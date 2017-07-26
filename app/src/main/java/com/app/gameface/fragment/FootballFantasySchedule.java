package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.adapter.FootballFantasySchduleAdpater;
import com.app.gameface.extra.Global;


public class FootballFantasySchedule extends Fragment {

    ListView listView;
    FootballFantasySchduleAdpater footballFantasySchduleAdpater;
    LinearLayout week_layout;
    RelativeLayout billboardAds,mainTitleLayout;
    TextView week_header_text,title_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_football_fantasy_schedule, container, false);

        loadData(view);

        onClick();

        return view;
    }


    public void loadData(View view)
    {

        listView=(ListView)view.findViewById(R.id.football_fantasy_schdule);
        footballFantasySchduleAdpater=new FootballFantasySchduleAdpater(getActivity());
        listView.setAdapter(footballFantasySchduleAdpater);


        MainActivity mainActivityLayout = (MainActivity) getActivity();


        title_text=(TextView)mainActivityLayout.findViewById(R.id.title_text);

        title_text.setVisibility(View.VISIBLE);

       /* week_layout=(LinearLayout)mainActivityLayout.findViewById(R.id.week_layout);

        week_layout.setVisibility(View.VISIBLE);*/

        week_header_text=(TextView) mainActivityLayout.findViewById(R.id.week_header_text);

    }

    public void onClick()
    {

       /* week_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Global.loadFragment(getActivity(),new WeekList());

            }
        });*/

    }


}
