package fr.quentinville.com.asynchronetask;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private String myUrlPhoto;
    private ImageView imageView;
    private EditText editText;
    public String search;
    private Gson gsonData = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ajout des composants de l'interface
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View view) throws InterruptedException, ExecutionException {
        Context context = getApplicationContext();

        textView.setText("not load");

        progressBar.setProgress(0);
        progressBar.setProgress(50);

        if (editText.getText().toString().isEmpty()) {
            search = ImageAdapter.loadResponse("bonbons");
        }
        else {
            search = ImageAdapter.loadResponse(editText.getText().toString().replace(" ", "%20"));
        }

        Response data = gsonData.fromJson(search, Response.class);

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
