package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferenceConfig= new SharedPreferenceConfig(getApplicationContext());
    }

    public void userlogout(View view) {
        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

}
