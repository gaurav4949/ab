package com.app.gameface.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.adapter.FootballFantasySchduleAdpater;


public class WeekSchedule extends Fragment {

    ListView listView;
    FootballFantasySchduleAdpater footballFantasySchduleAdpater;
    LinearLayout week_layout;
    RelativeLayout billboardAds,mainTitleLayout,top_left_ic_layout,top_right_ic_layout;
    TextView title_text;
    ImageView top_left_ic,top_right_ic;
    String i;

    public WeekSchedule(String i) {
       this.i=i ;
    }


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

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);

      //  week_layout=(LinearLayout)mainActivityLayout.findViewById(R.id.week_layout);

        top_left_ic_layout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);
        top_left_ic_layout.setVisibility(View.VISIBLE);
        top_left_ic=(ImageView)mainActivityLayout.findViewById(R.id.top_left_ic);
        top_left_ic.setImageResource(R.drawable.back);

        top_right_ic_layout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        top_right_ic=(ImageView)mainActivityLayout.findViewById(R.id.top_right_ic);
        top_right_ic.setImageResource(R.drawable.gf15a);
        top_right_ic_layout.setVisibility(View.VISIBLE);

       // week_layout.setVisibility(View.GONE);

        title_text=(TextView)mainActivityLayout.findViewById(R.id.title_text);

        title_text.setText(i);

    }

    public void onClick()
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