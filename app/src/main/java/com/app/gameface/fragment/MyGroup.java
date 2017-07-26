package com.app.gameface.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;

import static com.app.gameface.R.drawable.dummy_ad;


public class MyGroup extends Fragment {


    TabLayout tabLayout;
    Context context;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout;
    ImageView topLeftImage,topRightImage;
    TextView titleText;
    LinearLayout internal_ad_layout;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_group, container, false);

        loadData(view);

        Log.e(" My Group"," my group");

        onClick();
        Global.home_status="";
        return view;
    }


    public void loadData(View v)
    {




        context=getActivity();

        tabLayout=(TabLayout)v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Groups"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts"));
        tabLayout.addTab(tabLayout.newTab().setText("Game Face"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /* to load  groups by default   */
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_group_frame,new Groups(MyGroup.this));
        fragmentTransaction.commit();

      /*  TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();*/

        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);


        titleText = (TextView) mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Game Face");   //set title for Home



        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);
        topLeftImage = (ImageView) mainActivityLayout.findViewById(R.id.top_left_ic);
        topRightImage = (ImageView) mainActivityLayout.findViewById(R.id.top_right_ic);

        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.VISIBLE);




        topLeftImage.setImageResource(R.drawable.back);



        topRightImage.setImageResource(R.drawable.create_group);




        billboardAds = (RelativeLayout) mainActivityLayout.findViewById(R.id.billboar_ads);
       // billboardAds.setBackgroundResource(R.drawable.dummy_ad);

        internal_ad_layout=(LinearLayout)mainActivityLayout.findViewById(R.id.internal_ad_layout);
        internal_ad_layout.setVisibility(View.GONE);

        RelativeLayout.LayoutParams head_params = (RelativeLayout.LayoutParams)billboardAds.getLayoutParams();

        head_params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
        int h=getActivity().getWindowManager().getDefaultDisplay().getHeight()*4;
        head_params.height = h/25;;
        billboardAds.setLayoutParams(head_params);

        billboardAds.setVisibility(View.VISIBLE);

    }

    public void onClick()
    {

        topRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Global.loadFragment(getActivity(),new CreateGroup());

            }
        });



        // On tab Click
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0)
                {

                   // showMessage(context,""+0);
                    loadInternalFragment(new Groups(MyGroup.this));



                }
                if(tab.getPosition()==1)
                {

                   /// showMessage(context,""+1);
                    loadInternalFragment(new Contacts());

                }
                if(tab.getPosition()==2)
                {

                   // showMessage(context,""+2);
                    loadInternalFragment(new GameFace());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        //on Back Click
        topLeftIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                fm.popBackStack();

            }
        });




    }
    public  void showMessage(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }



    private void loadInternalFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.my_group_frame,fragment);
        fragmentTransaction.commit();


    }



}
