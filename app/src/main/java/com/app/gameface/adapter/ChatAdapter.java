package com.app.gameface.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ajit on 7/24/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {



    Context context;
     SharedPreferences sharedPreferences;
    ArrayList<HashMap<String ,String>> arrayList;
    String user_id;


    public ChatAdapter(Context context,ArrayList<HashMap<String ,String>> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,MODE_PRIVATE);
        this.user_id=sharedPreferences.getString(Global.USER_ID,"");
    }


    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_screen_list_view,null);

        return new ChatAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChatAdapter.MyViewHolder holder, final int position) {


        if(arrayList.get(position).get("sender_id").equalsIgnoreCase(user_id))
        {
            holder.frndChat.setVisibility(View.VISIBLE);
            holder.frndTxt.setText(arrayList.get(position).get("message"));

            holder.userChat.setVisibility(View.GONE);
            holder.userTxt.setVisibility(View.GONE);
        }
        else
        {
            holder.frndChat.setVisibility(View.GONE);


            holder.userChat.setVisibility(View.VISIBLE);
            holder.userTxt.setText(arrayList.get(position).get("message"));

            Glide.with(context).load(arrayList.get(position).get("sender_image"))
                    // .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            holder.userImg.setImageDrawable(resource);
                        }
                    });

        }


    }

    @Override
    public int getItemCount()
    {

        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        RelativeLayout userChat,frndChat;
        TextView frndTxt,userTxt;
        ImageView userImg;

        public MyViewHolder(View itemView) {
            super(itemView);

            userChat=(RelativeLayout)itemView.findViewById(R.id.userChat);
            frndChat=(RelativeLayout)itemView.findViewById(R.id.frndChat);
            frndTxt=(TextView) itemView.findViewById(R.id.frndTxt);
            userTxt=(TextView) itemView.findViewById(R.id.userTxt);
            userImg=(ImageView) itemView.findViewById(R.id.userImg);

        }
    }


}