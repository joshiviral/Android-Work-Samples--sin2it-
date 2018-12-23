package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceConfig sharedpreferenceConfig;
    private EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferenceConfig = new SharedPreferenceConfig(getApplicationContext());

        username=findViewById(R.id.etx_username);
        password=findViewById(R.id.etx_password);

        if(sharedpreferenceConfig.readLoginStatus())
        {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }
    public void loginUser(View view)
    {
        String user_name=username.getText().toString();
        String user_password=password.getText().toString();



        if(user_name.equals(getResources().getString(R.string.user_name))&& user_password.equals(getResources().getString(R.string.password)))
        {
            startActivity(new Intent(this,LoginActivity.class));
             sharedpreferenceConfig.writeLoginStatus(true);
             finish();
        }
        else
            {
                Toast.makeText(this,"LoginFailed..",Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
            }
    }
}