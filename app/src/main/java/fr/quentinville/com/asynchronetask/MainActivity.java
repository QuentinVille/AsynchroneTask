package fr.quentinville.com.asynchronetask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private String myUrlPhoto;
    private ImageView imageView;
    private EditText editText;
    private Gson gsonData = new Gson();
    public String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ajout des composants de l'interface
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);
    }

    private String loadResponse(String searchText) throws ExecutionException, InterruptedException {
        HttpGetter httpGetter = new HttpGetter();
        try {
            URL url = new URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=6a931a15d733ce7b2294ccab06f5cfcd&text="+searchText+"&format=json&nojsoncallback=1");
            httpGetter.execute(url);
            String s = httpGetter.get();
            // Log.w("s", s);
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
        Context context = getApplicationContext();

        textView.setText("not load");

        progressBar.setProgress(0);
        progressBar.setProgress(50);

        if (editText.getText().toString().isEmpty()) {
            search = loadResponse("bonbons");
        }
        else {
            search = loadResponse(editText.getText().toString().replace(" ","%20"));
        }

        Response data = gsonData.fromJson(search, Response.class);

//        Picasso.with(context)
//                .load(data.getPhotos().getPhoto().get(0).imageUrl())
//                .into(imageView);

        textView.setText("load");

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
