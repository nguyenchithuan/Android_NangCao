package edu.poly.dinhvivakiemtramang;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Demo3CheckNetwork {

    Context context;

    public Demo3CheckNetwork(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dangKyMang() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();

            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                    // đang kết nối
                    Demo3NetworkAvailable.isNetWorkConnected = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    super.onLost(network);
                    // ko kết nối
                    Demo3NetworkAvailable.isNetWorkConnected = false;
                }
            });
        } catch (Exception e) {
            Demo3NetworkAvailable.isNetWorkConnected = false;
        }
    }
}
