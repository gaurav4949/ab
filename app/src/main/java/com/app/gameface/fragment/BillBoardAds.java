package com.app.gameface.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.adapter.AdsAdapter;
import com.app.gameface.adapter.AdsPageAdapter;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class BillBoardAds extends Fragment {


    RecyclerView adsRecyclrView;
    AdsAdapter adsAdapter;
    Spinner adsCategorySpinner;
    Context context;
    RelativeLayout mainTitleLayout,billboardAds;
    ImageView topLeftImage,topRightImage;
    TextView titleText;
    ViewPager viewPager;
     SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_bill_board_ads, container, false);

        loadData(view);
        onClick();

        return view;
    }

    public void loadData(View view)
    {

        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);


        titleText = (TextView) mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Billboard Ads Menu");   //set title for Home

        topLeftImage = (ImageView) mainActivityLayout.findViewById(R.id.top_left_ic);
        topLeftImage.setVisibility(View.VISIBLE);
        topLeftImage.setImageResource(R.mipmap.ic_back);


        topRightImage = (ImageView) mainActivityLayout.findViewById(R.id.top_right_ic);

        topRightImage.setVisibility(View.GONE);


        billboardAds = (RelativeLayout) mainActivityLayout.findViewById(R.id.billboar_ads);
        billboardAds.setVisibility(View.GONE);


        context=getActivity();

        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF, Context.MODE_PRIVATE);
        adsRecyclrView=(RecyclerView)view.findViewById(R.id.ads_recyclerView);







        adsCategorySpinner=(Spinner)view.findViewById(R.id.ads_categogry_spinner);
       //Log.e("herererere",""+);

     //   adsCategorySpinner.setSelection(4);


        editor= getActivity().getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE).edit();


        new RetrofitBase(getActivity()).getAdsCategorienResponse(sharedPreferences.getString(Global.USER_ID,"")
                    , sharedPreferences.getString(Global.TOKEN,""),adsCategorySpinner);


        viewPager=(ViewPager) mainActivityLayout.findViewById(R.id.ads_viewpager);
        //loadAdsImages();

        }

    public void onClick()
    {

        adsCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                loadAdsImages(String.valueOf(i+1));

                editor.putString(Global.ADS_CATEGORY, String.valueOf(i+1));

                editor.commit();


                Log.e("After",""+sharedPreferences.getString(Global.ADS_CATEGORY,"1"));
                new RetrofitBase(getActivity()).getAds(getActivity(),sharedPreferences.getString(Global.USER_ID,"")
                       , sharedPreferences.getString(Global.TOKEN,""),sharedPreferences.getString(Global.ADS_CATEGORY,"1"),viewPager);
           //     Log.e("sdsdasddasd",""+Global.ads_images.size());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {



            }
        });



       /* topLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm=getFragmentManager();
                fm.popBackStack();

            }
        });*/


    }

    public void loadAdsImages(String cat_id)
    {

        Log.e("qwerty","Qwerty");
        Log.e("user_id",""+sharedPreferences.getString(Global.USER_ID,""));
        Log.e("token",""+sharedPreferences.getString(Global.TOKEN,""));
        Log.e("cat_id",""+cat_id);



        new  RetrofitBase(context).getAdsResponse(BillBoardAds.this,context,sharedPreferences.getString(Global.USER_ID,"")
                , sharedPreferences.getString(Global.TOKEN,""),cat_id,adsRecyclrView);


    }


}
