package com.app.gameface.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;


public class GameFace extends Fragment {


    ImageView topLeftImage,topRightImage;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout,refresh_bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_game_face, container, false);

        loadData();


        return view;
    }

    public void loadData()
    {
        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);


        topLeftImage = (ImageView) mainActivityLayout.findViewById(R.id.top_left_ic);
        topRightImage = (ImageView) mainActivityLayout.findViewById(R.id.top_right_ic);

        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);
        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.GONE);



        topLeftImage.setImageResource(R.drawable.back);

        refresh_bt=(RelativeLayout)mainActivityLayout.findViewById(R.id.refresh_bt);
        refresh_bt.setVisibility(View.GONE);


    }


    public void onClick()
    {

/*

        topLeftIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm=getFragmentManager();
                fm.popBackStack();

            }
        });
*/

    }


}
