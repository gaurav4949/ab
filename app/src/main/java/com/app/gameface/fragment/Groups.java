package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.adapter.GroupsAdapter;
import com.app.gameface.extra.Global;

import java.util.concurrent.Callable;


public class Groups extends Fragment {


    RecyclerView groupsRecyclerView;
    GroupsAdapter groupsAdapter;
    Context context;

    ImageView topLeftImage,topRightImage;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout,refresh_bt;

    Fragment fragment;

    public Groups(Fragment fragment)
    {


        this.fragment=fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_groups, container, false);

        loadData(view);

        onClick();
        Global.home_status="";
        return view;

    }

    public void loadData(View view)
    {

        context=getActivity();

        groupsRecyclerView=(RecyclerView)view.findViewById(R.id.groups_recycler_view);

       groupsAdapter=new GroupsAdapter(getActivity(),Global.Group_list,fragment);

        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(context);

        groupsRecyclerView.setLayoutManager(mLayoutManager);

        groupsRecyclerView.setAdapter(groupsAdapter);




        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);




        topLeftImage = (ImageView) mainActivityLayout.findViewById(R.id.top_left_ic);
        topLeftImage.setVisibility(View.VISIBLE);
        topLeftImage.setImageResource(R.drawable.back);


        topRightImage = (ImageView) mainActivityLayout.findViewById(R.id.top_right_ic);
        topRightImage.setImageResource(R.drawable.create_group);
        topRightImage.setVisibility(View.VISIBLE);


        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.VISIBLE);

        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);
        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.VISIBLE);


        refresh_bt=(RelativeLayout)mainActivityLayout.findViewById(R.id.refresh_bt);
        refresh_bt.setVisibility(View.GONE);

    }

    public void onClick()
    {

        /*topLeftIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm=getFragmentManager() ;
                fm.popBackStack();
            }
        });*/

        topRightIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Global.loadFragment(getActivity(),new CreateGroup());

            }
        });




    }





}
