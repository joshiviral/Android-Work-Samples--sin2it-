package com.example.viraljoshi.stepin2it;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;
    @BindView(R.id.btn_apiCall)
    Button btn_apicall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());
        btn_apicall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardActivity.this, ApiCallActivity.class);
                DashboardActivity.this.startActivity(myIntent);
            }
        });

//if the user has already logged in and terminates the activity, and if the user starts the app, it will retain users data and welcome profile will be displayed
        preferenceConfig = SharedPreferenceConfig.getInstance(getApplicationContext());
        new HttpPostAsyncTask().execute("http://my-json-server.typicode.com/joshiviral/StepIn2ItDemo/productData");

    }

    //when the logout button is pressed, user will be logged out and the user will be redirected to login activity
    public void userlogout(View view) {
        preferenceConfig.writeLoginStatus(false);
        Intent intent = new Intent(DashboardActivity.this, ApiCallActivity.class);
        startActivity(intent);
    }

    public class HttpPostAsyncTask extends AsyncTask<String, Void, String> {
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
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray rootjsonArray = new JSONArray(s);
                for (int i = 0; i < rootjsonArray.length(); i++) {

                    JSONObject productjsonObject = rootjsonArray.getJSONObject(i);
                    String productname = productjsonObject.getString("productId");
                    Timber.i("Product=" + i);
                    String weight = productjsonObject.getString("weight");
                    Timber.i("Weight=" + weight);
                    JSONArray images = productjsonObject.getJSONArray("images");
                    Timber.i("Images=" + images);
                    String phone = productjsonObject.getString("phone");
                    Timber.i("Phone=" + phone);
                    String web = productjsonObject.getString("web");
                    Timber.i("Web=" + web);
                    double price = productjsonObject.getDouble("price");
                    Timber.i("Price=" + price);
                    JSONArray tags = productjsonObject.getJSONArray("tags");
                    Timber.i("Tags=" + tags);

                    JSONObject dimensionsjObject = productjsonObject.getJSONObject("dimensions");
                    double length = dimensionsjObject.getDouble("length");
                    Timber.i("Lenght=" + length);
                    double widthObj = dimensionsjObject.getDouble("width");
                    Timber.i("Width=" + widthObj);
                    double height = dimensionsjObject.getDouble("height");
                    Timber.i("Height=" + height);

                    JSONObject warehouseLocationObj = productjsonObject.getJSONObject("warehouseLocation");
                    double latitude = warehouseLocationObj.getDouble("latitude");
                    Timber.i("Latitude=" + latitude);
                    double longitude = warehouseLocationObj.getDouble("longitude");
                    Timber.i("Longitude=" + longitude);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "" + s);

        }
    }
}