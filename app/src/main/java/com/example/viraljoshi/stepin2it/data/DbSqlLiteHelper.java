package com.example.viraljoshi.stepin2it.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.viraljoshi.stepin2it.model.Product;

import java.util.ArrayList;

public class DbSqlLiteHelper {
    private static SQLiteDatabase db;

    private static DbSqlLiteHelper instance;

    private DbSqlLiteHelper() {

    }

    public static DbSqlLiteHelper getInstance(Context context) {
        if (instance == null) {

            instance = new DbSqlLiteHelper();
            db = new DbSqlLiteOpenHelper(context).getWritableDatabase();
        }
        return instance;
    }

    public void insertProducts(ArrayList<Product> productArraylist) {
        for (Product product : productArraylist) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(IDBConstants.Product.PRODUCT_ID, product.getProductId());
            contentValues.put(IDBConstants.Product.PRODUCT_NAME, product.getName());
            contentValues.put(IDBConstants.Product.PRODUCT_DESCRIPTION, product.getDescription());
            contentValues.put(IDBConstants.Product.PHONE, product.getPhone());
            contentValues.put(IDBConstants.Product.WEB, product.getWeb());
            contentValues.put(IDBConstants.Product.PRICE, product.getPrice());
            db.insert(IDBConstants.Product.TABLE_NAME, null, contentValues);

        }


    }

    public ArrayList<Product> readProducts() {

        ArrayList<Product> productArrayList = null;


        Cursor cursor = db.query(IDBConstants.Product.TABLE_NAME, null, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {

            productArrayList = new ArrayList<Product>();
            do {
                Product product = new Product();
                product.setProductId(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.PRODUCT_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.PRODUCT_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.PRODUCT_DESCRIPTION)));
                product.setPhone(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.PHONE)));
                product.setPrice(cursor.getInt(cursor.getColumnIndex(String.valueOf(IDBConstants.Product.PRICE))));
              //  product.setImages(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.IMAGES)));
                product.setWeb(cursor.getString(cursor.getColumnIndex(IDBConstants.Product.WEB)));

                productArrayList.add(product);

            } while (cursor.moveToNext());
            cursor.close();

        }
        return productArrayList;
    }
}