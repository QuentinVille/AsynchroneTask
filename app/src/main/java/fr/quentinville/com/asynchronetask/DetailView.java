package fr.quentinville.com.asynchronetask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailView extends ActionBarActivity {
    private Context dContext;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

//      Ajout des composants de l'interface
        imageView = (ImageView) findViewById(R.id.imageViewDetail);
//      Affiche l'image sur laquelle on a cliqué dans la textView
        Picasso.with(dContext)
                .load(getIntent().getExtras().getString("photoUrl"))
                .into(imageView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_settings:
                String myUrl = getIntent().getExtras().getString("photoUrl");
                String myTitle = getIntent().getExtras().getString("photoTitle");
                String myOwner = getIntent().getExtras().getString("photoOwner");
                Toast.makeText(DetailView.this, "Title : " + myTitle + "\nOwner : " + myOwner + "\nUrl : " + myUrl
                        , Toast.LENGTH_LONG).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
