package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;

import java.util.ArrayList;
import java.util.HashMap;


public class GroupMembersList extends Fragment {


    ListView contactsListview;
    RelativeLayout back_layout;
    ArrayList<String > contacts=new ArrayList<>();


    public GroupMembersList( ArrayList<String> contacts)
    {
        this.contacts=contacts;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_group_members_list, container, false);

        loadData(view);

        onCancelClick();

        return view;
    }


    public  void loadData(View view)
    {
        contactsListview=(ListView)view.findViewById(R.id.contactsListview);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, contacts);




        contactsListview.setAdapter(arrayAdapter);


        back_layout=(RelativeLayout) view.findViewById(R.id.back_layout);




    }


    public  void onCancelClick()
    {
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.popBackStack();

            }
        });

    }

}