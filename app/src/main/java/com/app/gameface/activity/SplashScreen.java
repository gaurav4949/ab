package com.app.gameface.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.Home;
import com.app.gameface.fragment.Login;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);

       final SharedPreferences sharedPreferences=getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE);

        Log.e("userid",sharedPreferences.getString(Global.USER_ID,"null"));
        Log.e("token",sharedPreferences.getString(Global.TOKEN,"null"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);


    }
}
