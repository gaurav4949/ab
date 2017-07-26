package com.app.gameface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.EditSchedule;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamSportScheduleAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    TextView gameNumber,opponentName,opponenAddress,date,time,textview_edit;


    ArrayList<HashMap<String,String>> schdule =new ArrayList<>();
    ArrayList<HashMap<String,String>> inschdule=new ArrayList<>();
    ArrayList<HashMap<String,String>> outSchdule=new ArrayList<>();


    public  TeamSportScheduleAdapter(Context context, ArrayList<HashMap<String,String>> schdule, ArrayList<HashMap<String,String>>
            inschdule,ArrayList<HashMap<String,String>> outSchdule)
    {
        this.context=context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.schdule=schdule;
        this.inschdule=inschdule;
        this.outSchdule=outSchdule;
    }
    @Override
    public int getCount() {
        return schdule.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.team_support_schedule_layout, parent, false);
        }

        gameNumber = (TextView) convertView.findViewById(R.id.Game_no);
        opponentName=(TextView)convertView.findViewById(R.id.opponent_name);
        opponenAddress=(TextView)convertView.findViewById(R.id.textview_address);
        date=(TextView)convertView.findViewById(R.id.textview_date);
        time=(TextView)convertView.findViewById(R.id.textview_time);
        textview_edit=(TextView)convertView.findViewById(R.id.textview_edit);

        gameNumber.setText(schdule.get(position).get("game_name"));

        opponentName.setText(schdule.get(position).get("opponent_name"));

        opponenAddress.setText(schdule.get(position).get("location"));

        date.setText(schdule.get(position).get("date"));


        time.setText(schdule.get(position).get("time"));


        textview_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Global.loadFragment(context,new EditSchedule());
            }
        });


        return convertView;
    }
}
