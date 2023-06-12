package com.example.myapplication.demo4n;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Demo4n3XuLyCallback {
    Context context;
    //khoi tao
    public Demo4n3XuLyCallback(Context context) {
        this.context = context;
    }
    //dang ky mang
    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized boolean dangKyMang()
    {
        try {
            ConnectivityManager connectivityManager
                    =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                    Demo4n3NetworkVariable.isNetworkConnected = true;
                }
                @Override
                public void onLost(@NonNull Network network) {
                    Demo4n3NetworkVariable.isNetworkConnected = false;
                }
            });
        }
        catch (Exception e)
        {
            Demo4n3NetworkVariable.isNetworkConnected = false;
        }
        return Demo4n3NetworkVariable.isNetworkConnected;
    }
}
