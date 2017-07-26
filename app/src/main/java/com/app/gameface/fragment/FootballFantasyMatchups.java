package com.app.gameface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.gameface.R;
import com.app.gameface.adapter.MatchUpsAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class FootballFantasyMatchups extends Fragment {



    ListView listView;
    MatchUpsAdapter matchUpsAdapter;
    ArrayList<HashMap<String,String>> matchups =new ArrayList<>();

    public  FootballFantasyMatchups( ArrayList<HashMap<String,String>> matchups)
    {

        this.matchups=matchups;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_football_fantasy_matchups, container, false);

        loadData(view);



        return view;

    }

    public void loadData(View view)
    {
        listView=(ListView)view.findViewById(R.id.matchup_list_view);
        matchUpsAdapter=new MatchUpsAdapter(getActivity(),matchups);
        listView.setAdapter(matchUpsAdapter);


    }



}
