package fr.quentinville.com.asynchronetask;

import android.content.SharedPreferences;

/**
 * Created by quentin on 12/04/2015.
 */
public class MyPreferenceManager {
    public String token;
    public String secret_token;
    public String rawResponse;

    public MyPreferenceManager(String token, String secret_token, String rawResponse) {
        this.token = token;
        this.secret_token = secret_token;
        this.rawResponse = rawResponse;
    }

    public static MyPreferenceManager MyPreferencesManager(SharedPreferences preferences){
        MyPreferenceManager result_value = new MyPreferenceManager(
                preferences.getString("token", "token"),
                preferences.getString("secret_token", "secret_token"),
                preferences.getString("rawResponse", "rawResponse")
        );

        System.out.println(preferences.getAll());
        return result_value;
    }

    public static MyPreferenceManager setMyPreferencesValues(SharedPreferences preferences, String token, String secret_token, String rawResponse){
        MyPreferenceManager myPrefs = new MyPreferenceManager(token,secret_token,rawResponse);
        return myPrefs;
    }

    public	String	getToken(){
        String result = "toto";

        return result;
    }

    public	String	getSecret(){
        String result = "toto";

        return result;
    }
    public	String	getRawResponse(){
        String result = "toto";

        return result;
    }
    public	void	setToken(String	token){

    }
    public	void	setSecret(String	token){

    }
    public	void	setRawResponse(String	token){

    }


}
