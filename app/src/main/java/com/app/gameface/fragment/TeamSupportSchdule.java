package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.app.gameface.R;
import com.app.gameface.adapter.TeamSportScheduleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamSupportSchdule extends Fragment {


    ListView listView;
    TeamSportScheduleAdapter teamSportScheduleAdapter;
     ArrayList<HashMap<String,String>> schdule =new ArrayList<>();
    ArrayList<HashMap<String,String>> inschdule=new ArrayList<>();
     ArrayList<HashMap<String,String>> outSchdule=new ArrayList<>();

    public  TeamSupportSchdule( ArrayList<HashMap<String,String>> schdule, ArrayList<HashMap<String,String>> inschdule,ArrayList<HashMap<String,String>> outSchdule)
    {
        this.schdule=schdule;
        this.inschdule=inschdule;
        this.outSchdule=outSchdule;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_team_support_schdule, container, false);

        loadData(view);

        return view;
    }

    public void loadData(View view)
    {

        listView=(ListView)view.findViewById(R.id.team_sport_group_schdule_list);
        teamSportScheduleAdapter=new TeamSportScheduleAdapter(getActivity(),schdule,inschdule,outSchdule);
        listView.setAdapter(teamSportScheduleAdapter);


    }

}
