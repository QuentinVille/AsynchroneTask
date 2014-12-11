package fr.quentinville.com.asynchronetask;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private TextView textView01;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ajout des composants
        textView = (TextView) findViewById(R.id.textView);
        textView01 = (TextView) findViewById(R.id.TextView01);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            progressBar.setProgress(0);
            String response = "";
            for (String url : urls) {
                progressBar.setProgress(30);
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    progressBar.setProgress(50);
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            progressBar.setProgress(100);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            textView01.setText(result);
        }
    }

    public void onClick(View view) throws InterruptedException {
        progressBar.setProgress(0);

        // Chargement de la page web
        DownloadWebPageTask task = new DownloadWebPageTask();
        textView.setText("Loading");
        textView.setText("Load");
        textView01.setText("Loading .........");
        task.execute(new String[]{"https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=1&nojsoncallback=1&format=json&tags=furet&api_key=45074180ed9c766da6cdd745043f1cdc"});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
