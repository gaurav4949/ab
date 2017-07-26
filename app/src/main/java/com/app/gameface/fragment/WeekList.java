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
import android.widget.ListView;
import android.widget.TextView;

import com.app.gameface.R;

public class WeekList extends Fragment {


    ListView weekList;
    TextView cancelTxt;
    String[] week={"Week 1","Week 2","Week 3","Week 4","Week 5","Week 6","Week 7","Week 8","Week 9","Week 10"
            ,"Week 11","Week 12","Week 13","Week 14","Week 15","Week 16","Week 17"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_week_list, container, false);

        loadData(view);

        onCancelClick();

        return view;
    }


    public  void loadData(View view)
    {
        weekList=(ListView)view.findViewById(R.id.week_list_view);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, week);

        weekList.setAdapter(arrayAdapter);


        cancelTxt=(TextView)view.findViewById(R.id.cancel);


    }


    public  void onCancelClick()
    {
        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.popBackStack();

            }
        });

    }

}
