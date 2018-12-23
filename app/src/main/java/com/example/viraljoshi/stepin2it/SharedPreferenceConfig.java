package com.example.viraljoshi.stepin2it;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public static final String PREF_NAME = "stepin2it_preference";
    public static final String LOGIN_STATUS = "login_status";

    //SharedPreference Initialization
    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    //This method will store the users data into the sharedpreference which will return boolean value and writes into memory in the form of sharedpreference
    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor is form of memory used to store data in the form of sharedpreference
        editor.putBoolean(LOGIN_STATUS, status);
        editor.commit();
    }
    //if the user has already logged in and terminates the activity, and if the user starts the app, it will retain users data and welcome profile will be displayed
    public boolean readLoginStatus() {
        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }
}