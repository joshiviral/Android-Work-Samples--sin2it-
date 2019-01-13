package com.example.viraljoshi.stepin2it.api;

import com.example.viraljoshi.stepin2it.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/joshiviral/StepIn2ItDemo/productData")
    Call<ArrayList<Product>> getProducts();
}