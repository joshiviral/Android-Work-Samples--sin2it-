package com.example.viraljoshi.stepin2it.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbSqlLiteOpenHelper extends SQLiteOpenHelper {
    public DbSqlLiteOpenHelper(@Nullable Context context) {
        super(context, IDBConstants.DB_NAME, null, IDBConstants.DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(IDBConstants.Product.CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
