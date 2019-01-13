package com.example.viraljoshi.stepin2it;

import org.json.JSONArray;
import org.json.JSONObject;

import timber.log.Timber;

public class Product {

    String productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    String productname;
    String weight;
    JSONArray images;
    String phone;
    String web;
    double price;
    JSONArray tags;
    JSONObject dimensionsjObject;
    double lenght;
    double widthObj;
    double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    double height;
    JSONObject warehouseLocationObj;
    double longitude;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public JSONArray getImages() {
        return images;
    }

    public void setImages(JSONArray images) {
        this.images = images;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public JSONArray getTags() {
        return tags;
    }

    public void setTags(JSONArray tags) {
        this.tags = tags;
    }

    public JSONObject getDimensionsjObject() {
        return dimensionsjObject;
    }

    public void setDimensionsjObject(JSONObject dimensionsjObject) {
        this.dimensionsjObject = dimensionsjObject;
    }

    public double getWidthObj() {
        return widthObj;
    }

    public void setWidthObj(double widthObj) {
        this.widthObj = widthObj;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public JSONObject getWarehouseLocationObj() {
        return warehouseLocationObj;
    }

    public void setWarehouseLocationObj(JSONObject warehouseLocationObj) {
        this.warehouseLocationObj = warehouseLocationObj;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }




}
