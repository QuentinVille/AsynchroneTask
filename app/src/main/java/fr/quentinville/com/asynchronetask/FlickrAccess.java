package fr.quentinville.com.asynchronetask;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Created by quentin on 12/04/2015.
 */
public class FlickrAccess {
    private static final String PROTECTED_RESOURCE_URL = "https://api.flickr.com/services/rest/";

//    public static void main(String[] args)
//    {
//         String apiKey = "6a931a15d733ce7b2294ccab06f5cfcd";
//         String apiSecret = "de20dfb6eb67f73f";
//         OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
//         Token requestToken, accessToken;
//         Verifier verifier;
//        Scanner in = new Scanner(System.in);
//
//        requestToken = FlickrAccess.getRequestToken(service);
//        FlickrAccess.getAuthorizationUrl(service,requestToken);
//        System.out.println("And paste the verifier here");
//        System.out.print(">>");
//        verifier = new Verifier(in.nextLine());
//
//        accessToken = FlickrAccess.getAcessToken(service, requestToken, verifier);
//
//        System.out.println(FlickrAccess.getRawResponse(accessToken));
//
//        // Requête pour récupérer les informations d'un utilisateur
//        org.scribe.model.Response query = FlickrAccess.getUserInfos(service, PROTECTED_RESOURCE_URL, accessToken);
//        System.out.println(query.getBody());

//        String apiKey = "6a931a15d733ce7b2294ccab06f5cfcd";
//        String apiSecret = "de20dfb6eb67f73f";
//        OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
//        // Replace these with your own api key and secret
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("=== Flickr's OAuth Modified ===");
//        System.out.println();
//
//        // Obtain the Request Token
//        System.out.println("Fetching the Request Token...");
//        Token requestToken = service.getRequestToken();
//        System.out.println("Got the Request Token!");
//        System.out.println();
//
//        System.out.println("Now go and authorize Scribe here:");
//        String authorizationUrl = service.getAuthorizationUrl(requestToken);
//        System.out.println(authorizationUrl + "&perms=read");
//        System.out.println("And paste the verifier here");
//        System.out.print(">>");
//        Verifier verifier = new Verifier(in.nextLine());
//        System.out.println();
//
//        // Trade the Request Token and Verfier for the Access Token
//
//
//        System.out.println("Ici il faut sauvegarder le token dans un élément qui garde les données. Vecteur/Hasmpa ou autre");
//        System.out.println("Il faut ensuite vérifier qu'il n'y a pas de token de sauvegarder pour un utilisateur donnée en vérifiant dans la sauvegarde des tokens. (plus tard les shared prefferences de l'utilisateur de l'applicaton Android)");
//        System.out.println("Si il y a un token pour cet utilsateur on le récupère. Sinon on fait :");
//        System.out.println("Trading the Request Token for an Access Token...");
//        Token accessToken = service.getAccessToken(requestToken, verifier);
//        System.out.println("Got the Access Token!");
//        System.out.println("Et on sauvegarde le token");
//        System.out.println("Sinon on fait ce que l'on veut. L'accès est donné au compte. On peut effectuer ce que l'on veut en utilisant le token et le secret obtenu.");
//
//
//        System.out.println("********************************************");
//        System.out.println("(Acces Token, secret: " + accessToken + " )");
//        System.out.println("(RawResponse: " + accessToken.getRawResponse() + ")");
//        System.out.println("********************************************");
//        System.out.println();
//
//        // Now let's go and ask for a protected resource!
//        System.out.println("On peut désomrais accéder à des ressources protégés comme le login...");
//        System.out.println("Il suffit d'explorer la doc de flickr et voir ce qui est fesable avec l'api");
//
//        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
//        request.addQuerystringParameter("method", "flickr.test.login");
//        service.signRequest(accessToken, request);
//        Response response = request.send();
//
//        System.out.println("Got it! Lets see what we found...");
//        System.out.println();
//
//        System.out.println(response.getBody());
//
//    }

    public static Token getRequestToken(OAuthService service){
        System.out.println("Fetching the Request Token...");
        Token requestToken = service.getRequestToken();
        System.out.println("Got the Request Token!");
        return requestToken;
    }

    public static String getAuthorizationUrl(OAuthService service, Token requestToken){
        System.out.println("Now go and authorize Scribe here:");
        String authorizationUrl = service.getAuthorizationUrl(requestToken);
        System.out.println(authorizationUrl + "&perms=read");
        System.out.println("And paste the verifier in the input");

        return authorizationUrl + "&perms=read";
    }

    public static Token getAcessToken(OAuthService service, Token requestToken, Verifier verifier){
        Token accessToken = service.getAccessToken(requestToken, verifier);
        return accessToken;
    }

    public static String getRawResponse(Token accessToken){
        return accessToken.getRawResponse();
    }

    public static Response getUserInfos(OAuthService service, String PROTECTED_RESOURCE_URL, Token accessToken){
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        request.addQuerystringParameter("method", "flickr.test.login");
        service.signRequest(accessToken, request);
        return request.send();

    }

}
