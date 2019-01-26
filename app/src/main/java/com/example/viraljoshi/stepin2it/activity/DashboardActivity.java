package com.example.viraljoshi.stepin2it.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.example.viraljoshi.stepin2it.R;
import com.example.viraljoshi.stepin2it.SharedPreferenceConfig;
import com.example.viraljoshi.stepin2it.adapter.ProductAdapter;
import com.example.viraljoshi.stepin2it.api.APIInterface;
import com.example.viraljoshi.stepin2it.api.ApiClient;
import com.example.viraljoshi.stepin2it.api.CustomItemInterface;
import com.example.viraljoshi.stepin2it.data.DbSqlLiteHelper;
import com.example.viraljoshi.stepin2it.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DashboardActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;

    @BindView(R.id.btn_apiCall)
    Button btn_apicall;

    @BindView(R.id.rv_product)
    public RecyclerView rvProduct;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        getProducts();

        btn_apicall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DashboardActivity.this, ApiCallActivity.class);
                DashboardActivity.this.startActivity(myIntent);
            }
        });

//if the user has already logged in and terminates the activity, and if the user starts the app, it will retain users data and welcome profile will be displayed
        preferenceConfig = SharedPreferenceConfig.getInstance(getApplicationContext());
        //  new HttpPostAsyncTask().execute("http://my-json-server.typicode.com/joshiviral/StepIn2ItDemo/productData");




    }

    private void getProducts() {
        ArrayList<Product> productArrayList = DbSqlLiteHelper.getInstance(DashboardActivity.this).readProducts();
        if (productArrayList != null && ! productArrayList.isEmpty()) {

            setrAdapter(productArrayList);
        } else {


            Call<ArrayList<Product>> call = apiInterface.getProducts();
            call.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    final ArrayList<Product> productArrayList = response.body();
                    setrAdapter(productArrayList);
//                for (Product product : productArrayList) {
//
//                    Timber.i("Product=" + product.getProductId());
//                    Timber.i("Product=" + product.getName());
//                    Timber.i("Product=" + product.getWeight());
//                    Timber.i("Product=" + product.getImages());
//                    Timber.i("Product=" + product.getPhone());
//                    Timber.i("Product=" + product.getPrice());
//                    Timber.i("Product=" + product.getWeb());
//                    Timber.i("Product=" + product.getTags());
//                    Timber.i("Product=" + product.getDimensions().getLength());
//                    Timber.i("Product=" + product.getDimensions().getHeight());
//                    Timber.i("Product=" + product.getDimensions().getWidth());
//                    Timber.i("Product=" + product.getWeb());
//                    Timber.i("Product=" + product.getWarehouseLocation().getLatitude());
//                    Timber.i("Product=" + product.getWarehouseLocation().getLongitude());
//                }


                    DbSqlLiteHelper d1 = DbSqlLiteHelper.getInstance(DashboardActivity.this);
                    d1.insertProducts(productArrayList);


                }


                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

                }
            });
        }
    }

    public void setrAdapter(ArrayList<Product> productArrayList) {

        ProductAdapter productAdapter = new ProductAdapter(DashboardActivity.this, productArrayList, new CustomItemInterface() {
            @Override
            public void onItemClick(Product product, int position) {
                Intent i = new Intent(DashboardActivity.this, ProductDisplayActivity.class);
                i.putExtra(ProductDisplayActivity.EXTRA_PRODUCT, product);
                startActivity(i);
            }
        });
        rvProduct.setAdapter(productAdapter);

    }


    //when the logout button is pressed, user will be logged out and the user will be redirected to login activity
    public void userlogout(View view) {
        preferenceConfig.writeLoginStatus(false);
        Intent intent = new Intent(DashboardActivity.this, ApiCallActivity.class);
        startActivity(intent);
    }

  /*  public class HttpPostAsyncTask extends AsyncTask<String, Void, String> {
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
                    String productId = productjsonObject.getString("productId");


                    String productname = productjsonObject.getString("name");

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

                    Product product = new Product();
                    product.setProductid(productId);
                    product.setProductname(productname);
                    product.setWeight(weight);
                    product.setImages(images);
                    product.setPhone(phone);
                    product.setPrice(price);
                    product.setWeb(web);
                    product.setTags(tags);
                    product.setLenght(length);
                    product.setWidthObj(widthObj);
                    product.setHeight(height);
                    product.setLongitude(longitude);
                    product.setLatitude(latitude);

                    Timber.i("Product=" + product.getProductid());
                    Timber.i("Product=" + product.getProductname());
                    Timber.i("Product=" + product.getWeight());
                    Timber.i("Product=" + product.getImages());
                    Timber.i("Product=" + product.getPhone());
                    Timber.i("Product=" + product.getPrice());
                    Timber.i("Product=" + product.getWeb());
                    Timber.i("Product=" + product.getTags());
                    Timber.i("Product=" + product.getLenght());
                    Timber.i("Product=" + product.getWidthObj());
                    Timber.i("Product=" + product.getHeight());
                    Timber.i("Product=" + product.getLatitude());
                    Timber.i("Product=" + product.getLongitude());


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "" + s);

        }
    } */
}