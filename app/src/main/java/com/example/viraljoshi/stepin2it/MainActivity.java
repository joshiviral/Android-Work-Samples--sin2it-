package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    //Sharedpreference class initialization
    private SharedPreferenceConfig sharedpreferenceConfig;

    @BindView(R.id.etx_username)
    EditText etxUsername;
    @BindView(R.id.etx_password)
    EditText etxPassword;

    public static final String USERNAME = "";
    public static final String Password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//Sharedpreference class initialization
        sharedpreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
//View tags initialization from layout file

//this method is used to read the users status whether it is logged in or not and if logged in it will show the welcome profile
        if (sharedpreferenceConfig.readLoginStatus()) {
            //new activity will start
            startActivity(new Intent(this, DashboardActivity.class));
            //Activity life will be destroyed
            finish();
        }
    }

    //this method checks from the string file whether the etxUsername and etxPassword are correct or not
    @OnClick(R.id.btn_login)
    void submit() {
        //takes input from the user in the form of srting
        String user_name = etxUsername.getText().toString();
        String user_password = etxPassword.getText().toString();

// if etxUsername and etxPassword are same from the string file, it will start new activity and retains its values in shared preference
        if (user_name.equals(getResources().getString(R.string.user_name)) && user_password.equals(getResources().getString(R.string.password))) {
            startActivity(new Intent(this, DashboardActivity.class));
            sharedpreferenceConfig.writeLoginStatus(true);
            finish();
            //is etxUsername and etxPassword is wrong, login is failed...
        } else {
            Toast.makeText(this, "LoginFailed..", Toast.LENGTH_SHORT).show();
            etxUsername.setText("");
            etxPassword.setText("");
        }
    }
}