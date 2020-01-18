package com.example.finintask.services;


import android.app.Application;
import android.content.Context;

import com.example.finintask.callback.NetworkCallback;


public class MyApplication extends Application {
    public NetworkCallback networkCallback;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(Context context) {
         networkCallback =(NetworkCallback)context;
    }
}