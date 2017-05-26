package com.signatic.cupid;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.signatic.model.User;

import java.util.HashMap;

/**
 * Created by DefaultAccount on 10/18/2016.
 */
public class SessionManager {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Cupid-App";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String UserLogin="UserLogin";

    // User name (make variable public to access from outside)

    private Gson gson;

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson=new Gson();

    }

    public void createLoginSession(User user){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        String json = gson.toJson(user);
        editor.putString(UserLogin, json);
        editor.commit();
        // commit changes
        editor.commit();
    }
    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        // user name
        user.put(UserLogin, pref.getString(UserLogin, null));

        return user;
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}
