package com.example.myapplication.demo4;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

//lop kiem tra ket noi
public class Demo43CheckNetwork {
    Context context;

    public Demo43CheckNetwork(Context context) {
        this.context = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized boolean dangKyMang()
    {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                    Demo43NetworkAvailable.isNetworkConnected = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    Demo43NetworkAvailable.isNetworkConnected=false;
                }
            });
        }
        catch (Exception e)
        {
            Demo43NetworkAvailable.isNetworkConnected=false;
        }

        return Demo43NetworkAvailable.isNetworkConnected;
    }
}