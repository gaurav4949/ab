package com.app.gameface.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.adapter.AdsPageAdapter;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.BillBoardAds;
import com.app.gameface.fragment.Home;
import com.app.gameface.fragment.Login;
import com.app.gameface.networkConnectivity.ConnectivityReceiver;
import com.app.gameface.networkConnectivity.ConnectivityReceiverListener;
import com.app.gameface.networkConnectivity.GameFace;
import com.app.gameface.webServices.RetrofitBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements ConnectivityReceiverListener
,Home.OnFragmentInteractionListener{


    RelativeLayout billboar_ads;
    ViewPager viewPager;
    SharedPreferences sharedPreferences;


    int currentPage = 0;
    Timer timer;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        sharedPreferences=getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE);
       // Global.loadFragment(MainActivity.this,new Login());   /*Load Login Fragement*/

        if (sharedPreferences.getString(Global.USER_ID,"").equals(""))
        {
            loadFragment(new Login());
        }
        else
        {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, new Home());
            fragmentTransaction.commit();

        }


         /*Load Login Fragement*/

        checkConnection();      //checkConnection on Main Activity



        viewPager=(ViewPager)findViewById(R.id.ads_viewpager);
        //viewPager.setEnabled(false);
       loadAdsImages();

        //PagerAdapter adapter = new AdsPageAdapter(MainActivity.this,Global.ads_images);
        //viewPager.setAdapter(adapter);



     /*   After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == Global.bottom_ads_images.size()-1) {
                 currentPage = 0;
                   // Log.e("here",""+currentPage);
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer .schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);


        billboar_ads=(RelativeLayout)findViewById(R.id.billboar_ads);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.app.gameface",PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
            //    Toast.makeText(getApplicationContext(),sign,Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }



        billboar_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (Global.home_status.equalsIgnoreCase("home"))
               {
                   Global.loadFragment(MainActivity.this,new BillBoardAds());
               }
               else {
                   Log.e("sfdf","fsdf");
                   //Toast.makeText(MainActivity.this, "Promotions", Toast.LENGTH_SHORT).show();
               }

            }
        });



    }

    public void loadFragment(Fragment fragment)
    {

        Fragment login=new Login();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,login);
        fragmentTransaction.commit();



    }

    public void checkConnection()
    {
        showSnackbar( ConnectivityReceiver.isConnected());
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected)
    {
        showSnackbar(isConnected);
    }


    @Override
    protected void onResume() {
        super.onResume();

        GameFace.getInstance().setConnectivityListener(this);

    }





    public void loadAdsImages()
    {
        Log.e("sadaghfghfghd",""+sharedPreferences.getString(Global.ADS_CATEGORY,"1"));
        new RetrofitBase(getApplicationContext()).getAds(MainActivity.this,sharedPreferences.getString(Global.USER_ID,"")
                , sharedPreferences.getString(Global.TOKEN,""),sharedPreferences.getString(Global.ADS_CATEGORY,"1"),viewPager);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
       Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.content_frame);
       /*if (fragment instanceof Home)
        {
            Log.e("HELLO","Home");
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            MainActivity.this.finish();
        }*/
      /*   if (fragment instanceof Login)
        {
            Log.e("HELLO","Login");
            Toast.makeText(this, "Hello Login", Toast.LENGTH_SHORT).show();
            MainActivity.this.finish();
        }*/

     /* int count = getSupportFragmentManager().getBackStackEntryCount();
         if (count>1)
        {
            super.onBackPressed();
        }
        else if (fragment instanceof Login)
         {
             finish();
         }
*/
/*
        final Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.content_frame);

       /* if(fragment instanceof Home)
        {

            Log.e("Home"," "+"Home");
            //finish();

           // loadFragment(new BillBoardAds());

        }
        else
        {


            Log.e(" no Home"," "+" no Home");
           //super.onBackPressed();

        }*/

    }


    public void showSnackbar(boolean isConnected)
    {

       final LinearLayout mainLayout=(LinearLayout)findViewById(R.id.main_activity);

        if(!isConnected)
        {

            Snackbar snackbar = Snackbar
                    .make(mainLayout, "No internet Connection!", Snackbar.LENGTH_LONG)
                    /*.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar = Snackbar.make(mainLayout, "No internet Connection!", Snackbar.LENGTH_SHORT);

                            snackbar.show();
                        }
                    })*/;

            snackbar.show();

        }




    }

    @Override
    public void onFragmentHomeInteraction(String s) {
       Global.home_status=s;


    }
}
