package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;


public class AttachmentFragment extends Fragment {


    TabLayout tabLayout;
    LinearLayout backBtLayout;
    RelativeLayout billboardAds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_attachment, container, false);

        loadId(view);

        onTabClick();


        onBackPress();

        return view;
    }

    public void loadId(View view)
    {

        tabLayout=(TabLayout)view.findViewById(R.id.attachment_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.file_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.location_selector));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calender_selector));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        backBtLayout=(LinearLayout)view.findViewById(R.id.back_bt);

        // to load default fragment
        loadFragment(new GalleryFragment());




        MainActivity mainActivityLayout = (MainActivity) getActivity();


         billboardAds = (RelativeLayout) mainActivityLayout.findViewById(R.id.billboar_ads);
        billboardAds.setVisibility(View.GONE);

    }

    public void onTabClick()
    {

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition())

                {

                    case 0 :
                    /* to load  groups by default   */
                    loadFragment(new GalleryFragment());
                    break;

                    case 1 :

                    loadFragment(new LocationFragment());
                    break;

                    case 2:

                    loadFragment(new EventScheduleFragment());
                     break;

                    default:
                    loadFragment(new GalleryFragment());
                    break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    public void loadFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.attachment_frame,fragment);
        fragmentTransaction.commit();

    }

    public void onBackPress()
    {
        backBtLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();
                fm.popBackStack();
            }});

    }

}
