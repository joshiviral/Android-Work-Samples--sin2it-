package com.example.viraljoshi.stepin2it;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class StepIn2itApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
