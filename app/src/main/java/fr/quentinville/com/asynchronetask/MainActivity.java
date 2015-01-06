package fr.quentinville.com.asynchronetask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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
        //Ajout des composants de l'interface
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageView = (ImageView) findViewById(R.id.ImageView01);

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

//        Show it.
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

    public void onClick(View view) throws InterruptedException, ExecutionException {
        Context context = getApplicationContext();

        progressBar.setProgress(0);

        createJSON();
        progressBar.setProgress(50);

        Picasso.with(context)
                .load(myUrlPhoto)
                .into(imageView);

        progressBar.setProgress(100);
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
