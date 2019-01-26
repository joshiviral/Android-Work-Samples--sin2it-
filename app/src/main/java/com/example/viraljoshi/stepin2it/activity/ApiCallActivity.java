package com.example.viraljoshi.stepin2it.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.viraljoshi.stepin2it.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiCallActivity extends AppCompatActivity {

    @BindView(R.id.txt_apiCall)
    TextView txt_apicall;

    @BindView(R.id.btn_comments)
    Button btn_comments;
    @BindView(R.id.btn_albums)
    Button btn_albums;
    @BindView(R.id.btn_photos)
    Button btn_photos;
    @BindView(R.id.btn_users)
    Button btn_users;
    @BindView(R.id.btn_todo)
    Button btn_todo;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apicall);
        ButterKnife.bind(this);
        txt_apicall.setMovementMethod(new ScrollingMovementMethod());

        btn_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiAsyncTask().execute("https://jsonplaceholder.typicode.com/comments");


            }
        });

        btn_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiAsyncTask().execute("https://jsonplaceholder.typicode.com/albums");
            }
        });

        btn_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiAsyncTask().execute("https://jsonplaceholder.typicode.com/photos");
            }
        });

        btn_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiAsyncTask().execute("https://jsonplaceholder.typicode.com/users");
            }
        });
        btn_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiAsyncTask().execute("https://jsonplaceholder.typicode.com/todos");
            }
        });


    }

    public class ApiAsyncTask extends AsyncTask<String, Void, String> {
        JSONObject postData;
        private static final String TAG = "Asynctask";


        @Override
        protected String doInBackground(String... params) {
            try {
                // This is getting the url from the string we passed in
                URL url = new URL(params[0]);

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("GET");


                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");

                // Send the post body
                if (this.postData != null) {
                    OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                    writer.write(postData.toString());
                    writer.flush();
                }

                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String response = convertInputStreamToString(inputStream);
                    return response;


                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("", e.getLocalizedMessage());
            }
            return null;


        }

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            return total.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            txt_apicall.setText(s);
        }
    }
}