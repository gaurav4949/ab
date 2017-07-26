package com.app.gameface.extra;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.app.gameface.R;
import com.app.gameface.fragment.FootballFantasySettings;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ajit on 7/3/2017.
 */

public class Global
{

    public static String URL="thttps://www.thegamefaceapp.com/ios_game_faceapp/Api/";

    public static String SHARED_PREF="game_face";

    public static String home_status="";
    public static String USER_ID="user_id";
    public static String USER_EMAIL="user_email";
    public static String USER_NAME="user_name";
    public static String TOKEN="token";
    public static String USER_PHONE="user_phone";
    public static String VERIFICATION_STATUS="v_status";
    public static String DEVICE_ID="device_id";
    public static String otp="";
    public static String login_status="0";
    public static String ADS_CATEGORY="ads_category";

    public static ArrayList<HashMap<String,String>> contacts;
    public static ArrayList<HashMap<String,String>> contacts_response;
    public static ArrayList<HashMap<String,String>>  ads_cat;
    public static ArrayList<HashMap<String,String>>  ads_images;
    public static ArrayList<HashMap<String,String>>  bottom_ads_images;
    public static ArrayList<HashMap<String,String>>  Group_list;

    public static Double lat;
    public static Double lng;
    public static String login_status_group_list="0";
    public  static String PREVIOUS="";
    public  static String GROUP_NAME="";
    public  static String AGE_GROUP="";
    public  static String GROUP_ID="";

    public static void loadFragment(Context context,Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment).addToBackStack(null);
        fragmentTransaction.commit();


    }


}
