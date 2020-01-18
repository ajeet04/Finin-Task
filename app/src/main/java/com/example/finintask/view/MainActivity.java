package com.example.finintask.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.manager.ConnectivityMonitor;
import com.example.finintask.R;
import com.example.finintask.callback.NetworkCallback;
import com.example.finintask.services.MyApplication;
import com.example.finintask.services.NetworkBroadCast;
import com.example.finintask.services.ServiceManager;
import com.example.finintask.utils.ApiConstants;
import com.example.finintask.view.fragment.MainFragment;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity implements NetworkCallback {
   private NetworkBroadCast networkBroadCast;
   private boolean isOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(MainFragment.TAG, null, false);
        networkBroadCast=new NetworkBroadCast();
        registerNetworkBroadcastForNougat();



    }



    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(networkBroadCast);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    private void updateFragment(boolean status) {
        isOnline=status;
        MainFragment fragment=(MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);
        if(fragment!=null){
            fragment.showConnectivityStatus(status);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();

    }
    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(networkBroadCast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(networkBroadCast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    @Override
    public void isConnected(boolean isOnline) {
      updateFragment(isOnline);
    }
}
