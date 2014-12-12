package fr.quentinville.com.asynchronetask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    //private TextView textView01;
    private ProgressBar progressBar;
    private String myUrlPhoto;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ajout des composants
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //textView01 = (TextView) findViewById(R.id.TextView01);
        imageView = (ImageView) findViewById(R.id.TextView01);

    }

    private void createJSON() {
        String jsonPhoto =
                "{"
                + "'id':'7258271356',"
                + "'owner':'28991435@N02',"
                + "'secret':'a65b777a5e',"
                + "'server':'8156',"
                + "'farm':9,"
                + "'title':'Ferret Shlomo in the frozen bathtube, that was used as a rain barrell - 2005',"
                + "'ispublic':1,"
                + "'isfriend':0,"
                + "'isfamily':0}";

        // Now do the magic.
        PhotoObject data = new Gson().fromJson(jsonPhoto, PhotoObject.class);

        myUrlPhoto = "https://farm"+ data.getFarm() + ".staticflickr.com/" + data.getServer()+ "/"+data.getId()+"_"+data.getSecret()+".jpg/";

        // Show it.
//        Log.w("test", data.getId());
//        Log.w("test", data.getOwner());
//        Log.w("test", data.getSecret());
//        Log.w("test", data.getServer());
//        Log.w("test", String.valueOf(data.getFarm()));
//        Log.w("test", data.getTitle());
//        Log.w("test", String.valueOf(data.getIspublic()));
//        Log.w("test", String.valueOf(data.getIsfriend()));
//        Log.w("test", String.valueOf(data.getIsfamily()));
    }

    private String loadWebPage() throws ExecutionException, InterruptedException {
        HttpGetter httpGetter = new HttpGetter();
        try {
            URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=1&nojsoncallback=1&format=json&tags=furet&api_key=6a931a15d733ce7b2294ccab06f5cfcd");
            httpGetter.execute(url);
            String s = httpGetter.get();
            Log.w("s", s);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }

        return httpGetter.get();
    }

    public void onClick(View view) throws InterruptedException, ExecutionException {
        progressBar.setProgress(0);

        createJSON();
        //String fluxHttp = loadWebPage();
        progressBar.setProgress(50);
        //textView01.setText(fluxHttp);

        Ion.with(imageView)
                //.placeholder(R.drawable.placeholder_image)
                //.error(R.drawable.error_image)
                .load(myUrlPhoto);

        progressBar.setProgress(100);



        //task.execute(new String[]{"https://api.flickr.com/services/rest/?method=flickr.photos.search&per_page=1&nojsoncallback=1&format=json&tags=furet&api_key=45074180ed9c766da6cdd745043f1cdc"});

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
            Context context = getApplicationContext();
            CharSequence text = "Coucou :) !";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
