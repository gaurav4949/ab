package com.app.gameface.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.FootballFantasySchedule;
import com.app.gameface.fragment.WeekSchedule;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.HashMap;


public class MatchUpsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    TextView weekNumber,opponentName;
    ImageView image;
    RelativeLayout matchup_layout;
    ArrayList<HashMap<String,String>> matchups =new ArrayList<>();
    public  MatchUpsAdapter(Context context, ArrayList<HashMap<String,String>> matchups)
    {
        this.context=context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.matchups=matchups;
    }
    @Override
    public int getCount() {
        return 17;
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
    public View getView(final int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.match_ups_layout, parent, false);
        }

        weekNumber = (TextView) convertView.findViewById(R.id.Textview_week);
        matchup_layout=(RelativeLayout)convertView.findViewById(R.id.matchup_layout);
        weekNumber.setText(matchups.get(position).get("name"));


        matchup_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.loadFragment(context,new WeekSchedule(matchups.get(position).get("name")));

            }
        });
       // opponentName=(TextView)convertView.findViewById(R.id.opponent_name);
        image=(ImageView)convertView.findViewById(R.id.image);

        Glide.with(context).load(matchups.get(position).get("image"))
                // .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        image.setImageDrawable(resource);
                    }
                });

        return convertView;
    }
}
