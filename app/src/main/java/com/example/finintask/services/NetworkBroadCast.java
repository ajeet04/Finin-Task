package com.example.finintask.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class NetworkBroadCast extends BroadcastReceiver {



    boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       if(checkInternet(context))
        {
            MyApplication.getInstance().networkCallback.isConnected(true);
        }
        else {
            MyApplication.getInstance().networkCallback.isConnected(false);

        }

    }

}