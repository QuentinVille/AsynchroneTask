package fr.quentinville.com.asynchronetask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class GridActivity extends ActionBarActivity {

    private Gson gsonData;
    private List<Photo> data;
    private ImageAdapter imageAdapter;
    private String tag;

    private GridView gridView;

    private String resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

//      Récupération des paramètre depuis l'activité appellante
        tag = getIntent().getExtras().getString("tag");
        //Resolution
        resolution = getIntent().getExtras().getString("resolution");

//      Ajout des composants de l'interface
        gridView = (GridView) findViewById(R.id.gridView);

//      Initialisation des varaibles
        gsonData = new Gson();
        data = new ArrayList<>();

//      Passage des paramètres à l'image adapter qui créera la gridView : la vue et la List de photo initalisée à Vide.
        imageAdapter = new ImageAdapter(this, data);
        gridView.setAdapter(imageAdapter);


//      Mise à jour de la liste de photos data à partir du mot recherché ("ski" par défaut si le champ est vide)
        data.clear(); //on remet le vecteur contenant les photos à zéro
        try {
            data.addAll(gsonData.fromJson(loadResponse(tag), Response.class).getPhotos().getPhoto()); //on ajoute toutes les photos dans la liste
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        imageAdapter.notifyDataSetChanged(); //on notifie l'imageAdapter de la gridView que le contenu qu'elle doit afficher à changé


//      Au moment du click sur la photo renvoit sur l'image
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Appel de la nouvelle activity
                Intent intent = new Intent(getBaseContext(), DetailView.class);

                // On lui passe les paramètres de la photo
                intent.putExtra("photoUrl",data.get(position).imageUrlResolution(resolution)); //ici on met l'image avec la resoltuion
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
                Toast.makeText(GridActivity.this, "" + data.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private static String loadResponse(String searchText) throws ExecutionException, InterruptedException {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
