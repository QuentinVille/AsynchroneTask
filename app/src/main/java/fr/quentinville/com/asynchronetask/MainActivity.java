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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener{
    private TextView textView;
    private ProgressBar progressBar;
    private EditText editText;

    // Elements pour l'authentification
    private MyPreferenceManager myPref;
    private Token token;
    private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";
    private String apiKey = "6a931a15d733ce7b2294ccab06f5cfcd";
    private String apiSecret = "de20dfb6eb67f73f";
    private OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
    private Token requestToken, accessToken;
    private Verifier verifier;

    private Spinner spinner;

    private String resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // On ajout les données de l'utilisateur dans l'application
//        SharedPreferences prefs = this.getSharedPreferences(
//                "fr.quentinville.com.asynchronetask", Context.MODE_PRIVATE);
//        // On fait l'appel dans les préférences voir si les données existe déjà
//        myPref = MyPreferenceManager.MyPreferencesManager(prefs);
//        // si il y en a pas on créé le jeton de connexion à l'aide de l'api scribe.org OAuth
//        if (myPref.getToken() == null && myPref.getSecret() == null && myPref.getRawResponse() == null) {
//            myPref = MyPreferenceManager.setMyPreferencesValues(prefs, myPref.getToken(), myPref.getSecret(), myPref.getRawResponse());
//        }
//
//        // Du coup dans la variable myPref les données de l'access token sont présente et il est ainsi possible de faire des appels à l'api flickr avec ce jeton.


//        requestToken = FlickrAccess.getRequestToken(service);
//        FlickrAccess.getAuthorizationUrl(service,requestToken);
//        FlickrAccess.getAcessToken(service, requestToken, verifier);
//        FlickrAccess.getRawResponse(accessToken);
//        // Requête pour récupérer les informations d'un utilisateur
//        org.scribe.model.Response query = FlickrAccess.getUserInfos(service, PROTECTED_RESOURCE_URL, accessToken);
//        System.out.println(query.getBody());

//      Ajout des composants de l'interface
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);

        // On défitnit sur le spinner un Listener pour le click sur un des items
        spinner.setOnItemSelectedListener(this);

        // Mise en place du spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.resolution, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinner.setAdapter(adapter);

    }

    public void onClick(View view) throws InterruptedException, ExecutionException, UnsupportedEncodingException {
        progressBar.setProgress(0);
        textView.setText("not load");

        progressBar.setProgress(25);
//      Vérification du paramètre de recherche (par défaut je décide de mettre le tag #warning)
        String tag = "warning";

//      On vérifie ce qui est entré dans le champ de recherche
        if (!editText.getText().toString().isEmpty()) {
            try {
                tag=  URLEncoder.encode(editText.getText().toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        progressBar.setProgress(50);

        // Appel de la nouvelle activity avec la gridView
        Intent intent = new Intent(getBaseContext(), GridActivity.class);
        // On lui passe les paramètres de la photo
        intent.putExtra("tag",tag);
        //Les paramètre de résolution (on pourrait lui passer un Bundle peut-etre ??)
        intent.putExtra("resolution",resolution);


        // On démarre la nouvelle activité
        startActivity(intent);

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

        Scanner in = new Scanner(System.in);

        requestToken = FlickrAccess.getRequestToken(service);
        FlickrAccess.getAuthorizationUrl(service,requestToken);
        System.out.println("And paste the verifier here");
        System.out.print(">>");
        Verifier verifier = new Verifier(in.nextLine());

        accessToken = FlickrAccess.getAcessToken(service, requestToken, verifier);

        System.out.println(FlickrAccess.getRawResponse(accessToken));

        // Requête pour récupérer les informations d'un utilisateur
        org.scribe.model.Response query = FlickrAccess.getUserInfos(service, PROTECTED_RESOURCE_URL, accessToken);
        System.out.println(query.getBody());

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, FlickrAccess.getRawResponse(accessToken)
                    , Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// Ce n'est pas cela
//       String value = (String) (String) parent.getItemAtPosition(position);
//        String para = "[x]+";
//        String[] resolution = value.split(para);
//        // il faudrait penser à la gestion des erreurs
//        mResolution = resolution[0];
//        sResolution = resolution[1];
//        tResoltion = resolution[2];
//        zResolution = resolution[3];
//        bResolution = resolution[4];
        resolution= (String) (String) parent.getItemAtPosition(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
