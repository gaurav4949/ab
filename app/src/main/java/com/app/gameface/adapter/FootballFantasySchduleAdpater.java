package com.app.gameface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.gameface.R;

/**
 * Created by ajit on 7/6/2017.
 */

public class FootballFantasySchduleAdpater extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    TextView opponentName1,opponentScore1, opponentName2, opponentScore2;

    public FootballFantasySchduleAdpater(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
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


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.footballfantasay_schdule_layout, parent, false);
        }


        return convertView;
    }
}