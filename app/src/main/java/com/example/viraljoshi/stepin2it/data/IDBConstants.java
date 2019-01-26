package com.example.viraljoshi.stepin2it.data;

public interface IDBConstants {

    String DB_NAME = "StepIn2It.db";
    int DB_VERSION = 1;
    String COMMA = ", ";

    interface Product {
        String TABLE_NAME = "product";
        String PRODUCT_ID = "product_id";
        String PRODUCT_NAME = "product_name";
        String PRODUCT_DESCRIPTION = "product_description";
        String PHONE = "phone";
        String IMAGES = "images";
        String WEB = "web";
        String PRICE = "price";
        String CREATE = "create table " + TABLE_NAME +
                " (" +
                PRODUCT_ID + " text " + COMMA
                + PRODUCT_NAME + " text " + COMMA
                + PRODUCT_DESCRIPTION + " text " + COMMA
                + PHONE + " text " + COMMA
                + PRICE + " integer " + COMMA
                + IMAGES + " text " + COMMA
                + WEB + " text "
                + ");";
    }


    interface ProductDimensions {
        String TABLE_NAME = "dimensions";
        String LENGHT = "length";
        String WIDTH = "width";
        String HEIGHT = "height";
        String CREATE = "create table " + TABLE_NAME +
                " (" +
                LENGHT + "integer" + COMMA
                + WIDTH + "integer" + COMMA +
                HEIGHT + "height" + " );" ;
    }
}
