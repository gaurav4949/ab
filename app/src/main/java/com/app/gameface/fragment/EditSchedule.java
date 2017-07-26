package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.gameface.R;


public class EditSchedule extends Fragment {

    RelativeLayout top_left_ic_layout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit_schedule, container, false);

        loadData(view);

        onBackClick();

        return view;
    }

    public  void loadData(View view)
    {

        top_left_ic_layout=(RelativeLayout)view.findViewById(R.id.top_left_ic_layout);

    }

    public void onBackClick()
    {

        top_left_ic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm=getFragmentManager();

                fm.popBackStack();

            }
        });


    }


}
