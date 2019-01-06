package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

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
    @BindView(R.id.pb_login)
    ProgressBar progressBar;
    @BindView(R.id.txt_validation)
    TextView txtValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//Sharedpreference class initialization
        sharedpreferenceConfig = SharedPreferenceConfig.getInstance(getApplicationContext());
//View tags initialization from layout file

//this method is used to read the users status whether it is logged in or not and if logged in it will show the welcome profile
        if (sharedpreferenceConfig.readLoginStatus()) {
            //new activity will start
            startActivity(new Intent(this, DashboardActivity.class));
            //Activity life will be destroyed
            finish();
        }
    }


    private boolean isValidEmailId() {
        String email = etxUsername.getText().toString().trim();
        if (email.isEmpty()) {
            etxUsername.setError("Username Cannot be empty");
            return false;
        } else if (Patterns.EMAIL_ADDRESS.matcher((CharSequence) etxUsername).matches()) {
            etxUsername.setError("Please Enter a valid email address");
            return false;
        } else {
            etxUsername.setError(null);
            return true;
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



        // new LoginAsynctask().execute(etxUsername.getText().toString(), etxPassword.getText().toString());
    }

   /* class LoginAsynctask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... params) {

            String username = params[0];
            String password = params[1];
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);

            }

            if (username.equals(getResources().getString(R.string.user_name)) && password.equals(getResources().getString(R.string.password))) {
                return true;
                //is etxUsername and etxPassword is wrong, login is failed...
            } else {

                return false;
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(),
                    "progress =" + values[0],
                    Toast.LENGTH_SHORT).show();


        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);

            if (result) {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                sharedpreferenceConfig.writeLoginStatus(true);
                finish();
                //is etxUsername and etxPassword is wrong, login is failed...
            } else {
                Toast.makeText(MainActivity.this, "LoginFailed..", Toast.LENGTH_SHORT).show();
                etxUsername.setText("");
                etxPassword.setText("");
            }
        }


    }*/


}