package com.app.gameface.networkConnectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ajit on 6/28/2017.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    public  static ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }



    @Override
    public void onReceive(Context context, Intent intent)

    {

        ConnectivityManager cm=(ConnectivityManager)GameFace.getInstance()
                .getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();

        boolean isConnected= activeNetwork!=null && activeNetwork.isConnectedOrConnecting();

        if(connectivityReceiverListener !=null)
        {

            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }


    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) GameFace.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


}
