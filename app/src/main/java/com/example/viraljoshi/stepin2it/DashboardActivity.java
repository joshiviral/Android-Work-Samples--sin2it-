package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//if the user has already logged in and terminates the activity, and if the user starts the app, it will retain users data and welcome profile will be displayed
        preferenceConfig = SharedPreferenceConfig.getInstance(getApplicationContext());
    }

    //when the logout button is pressed, user will be logged out and the user will be redirected to login activity
    public void userlogout(View view) {
        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}