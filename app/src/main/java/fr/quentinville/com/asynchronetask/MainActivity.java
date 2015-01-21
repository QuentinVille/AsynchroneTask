package fr.quentinville.com.asynchronetask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private EditText editText;
    private Gson gsonData;
    private List<Photo> data;
    private ImageAdapter imageAdapter;
    private Spinner spinner;

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Ajout des composants de l'interface
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);

        gridView = (GridView) findViewById(R.id.gridView);

//      Initialisation des varaibles
        gsonData = new Gson();
        data = new ArrayList<>();


//      Passage des paramètres à l'image adapter qui créra la gridView : la vue et la List de photo initalisée à Vide.
        imageAdapter = new ImageAdapter(this, data);
        gridView.setAdapter(imageAdapter);

//      Au moment du click sur la photo renvoit sur l'image
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Appel de la nouvelle activity
                Intent intent = new Intent(getBaseContext(), DetailView.class);

                // On lui passe les paramètres de la photo
                intent.putExtra("photoUrl",data.get(position).imageUrl());
                intent.putExtra("photoTitle",data.get(position).getTitle());
                intent.putExtra("photoOwner",data.get(position).getOwner());

                // On démarre la nouvelle activité
                startActivity(intent);

            }
        });

//      Si click long on affiche le titre de l'image dans un toast
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                Toast.makeText(MainActivity.this, "" + data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//////////////////////////////
ArrayList<String> myStringArray;
        myStringArray = new ArrayList<>();
        myStringArray.add(0,"toto");
        myStringArray.add(1,"titi");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,	myStringArray);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinner.setAdapter(adapter);

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


    public void onClick(View view) throws InterruptedException, ExecutionException, UnsupportedEncodingException {
        progressBar.setProgress(0);
        textView.setText("not load");

//      Vérification du paramètre de recherche
        String tag = "warning";

//      On vérifie ce qui est entré dans le champ de recherche
        if (!editText.getText().toString().isEmpty()) {
            try {
                tag=  URLEncoder.encode(editText.getText().toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        progressBar.setProgress(25);

//      Mise à jour de la liste de photos data à partir du mot recherché ("ski" par défaut si le champ est vide)
        data.clear(); //on remet le vecteur contenant les photos à zéro
        data.addAll(gsonData.fromJson(loadResponse(tag), Response.class).getPhotos().getPhoto()); //on ajoute toutes les photos dans la liste

        imageAdapter.notifyDataSetChanged(); //on notifie l'imageAdapter de la gridView que le contenu qu'elle doit afficher à changé

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
            Toast.makeText(MainActivity.this, "Coucou :) !"
                    , Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
