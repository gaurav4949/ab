package com.app.gameface.networkConnectivity;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

/**
 * Created by ajit on 6/28/2017.
 */

public class GameFace extends MultiDexApplication
{

    private static GameFace mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized GameFace getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
