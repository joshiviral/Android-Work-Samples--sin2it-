package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferenceConfig sharedpreferenceConfig;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedpreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedpreferenceConfig.readLoginStatus()) {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
