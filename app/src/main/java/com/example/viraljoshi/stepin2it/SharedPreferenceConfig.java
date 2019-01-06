package com.example.viraljoshi.stepin2it;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;
    private Context context;

    private static SharedPreferenceConfig instance;

    public static final String PREF_NAME = "stepin2it_preference";
    public static final String LOGIN_STATUS = "login_status";

    private SharedPreferenceConfig() {
    }

    public static SharedPreferenceConfig getInstance(Context context) {
        if (instance == null) {

            instance = new SharedPreferenceConfig();
            context = context;
            sSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            sEditor = sSharedPreferences.edit();
        }
        return instance;
    }

    //SharedPreference Initialization

    //This method will store the users data into the sharedpreference which will return boolean value and writes into memory in the form of sharedpreference
    public void writeLoginStatus(boolean status) {

        //editor is form of memory used to store data in the form of sharedpreference
        sEditor.putBoolean(LOGIN_STATUS, status);
        sEditor.commit();

    }

    //if the user has already logged in and terminates the activity, and if the user starts the app, it will retain users data and welcome profile will be displayed
    public boolean readLoginStatus() {
        return sSharedPreferences.getBoolean(LOGIN_STATUS, false);
    }
}