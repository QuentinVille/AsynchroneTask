package fr.quentinville.com.asynchronetask;

    import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


    /**
     * La classe nous permettant de "lire" une URL.
     * @author jub
     *
     *
     * Exemple d'utilisation :
     * </br>
     * <code>
     * HttpGetter httpGetter = new HttpGetter();</br>
     *				try {</br>
     *					URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=London,uk");</br>
     *					httpGetter.execute(url);</br>
     *					String s = httpGetter.get();</br>
     *					Log.w("s",s);</br>
     *				} catch (MalformedURLException e) {</br>
     *
     *					e.printStackTrace();</br>
     *				} catch (InterruptedException e) {</br>
     *
     *					e.printStackTrace();</br>
     *				} catch (ExecutionException e) {</br>
     *
     *					e.printStackTrace();</br>
     *				}</br>
     *</code>
     */

/**
 * Created by quentin on 12/12/2014.
 */
public class HttpGetter extends AsyncTask<URL, Void, String> {
        private final static int timeoutConnection = 3000;
        private final static int timeoutSocket = 5000;
        @Override
        protected String doInBackground(URL... urls) {

            StringBuilder builder = new StringBuilder();
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            HttpClient client = new DefaultHttpClient(httpParameters);


            URI uri;
            try {
                uri = urls[0].toURI();

                HttpGet httpGet = new HttpGet(uri);


                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    //Log.w("Getter", "Your data: " + builder.toString()); //response data
                } else {

                    //Log.w("Getter", "Failed to download file");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }

            catch (URISyntaxException e1) {

                e1.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return builder.toString();
        }
}
