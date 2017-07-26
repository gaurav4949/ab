package com.app.gameface.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.FootballFantasySettings;
import com.app.gameface.fragment.GroupChatFragment;
import com.app.gameface.fragment.TeamSportSettings;
import com.app.gameface.webServices.RetrofitBase;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.HashMap;


public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder>

{


    Context context;
    ArrayList<HashMap<String,String>> groupList=new ArrayList<>();
    Fragment fragment;
    SharedPreferences sharedPreferences;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_main_layout,null);

        return new MyViewHolder(view);
    }

    public  GroupsAdapter(Context context, ArrayList<HashMap<String,String>> groupList,Fragment fragment)
    {


        this.context=context;
        this.groupList=groupList;
        this.fragment=fragment;
        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,Context.MODE_PRIVATE);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        Glide.with(context).load(groupList.get(position).get("group_image"))
                // .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.groupImage.setImageDrawable(resource);
                    }
                });

        //holder.groupImage.setImageResource(groupImages[position]);

        holder.groupName.setText(groupList.get(position).get("group_name"));

        holder.chatCount.setText(groupList.get(position).get("count"));

        holder.date.setText(groupList.get(position).get("group_date"));



        if(groupList.get(position).get("group_type").equalsIgnoreCase("GG"))
        {
            holder.groupType.setText("General Group");

        }
        else if(groupList.get(position).get("group_type").equalsIgnoreCase("FFG"))
                {
                    holder.groupType.setText("Football Fantasy Group");
                }
                else if(groupList.get(position).get("group_type").equalsIgnoreCase("TSG"))
                        {
                            holder.groupType.setText("Team Sport Group");

                        }

        holder.groupChatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Global.loadFragment(context,new GroupChatFragment(holder.groupType.getText().toString()
                ,groupList.get(position).get("group_name"),groupList.get(position).get("group_image"),
                        groupList.get(position).get("group_id")));

                Global.GROUP_NAME=groupList.get(position).get("group_name");
                Global.AGE_GROUP=groupList.get(position).get("age_group");
                Global.GROUP_ID= groupList.get(position).get("group_id");

               /* FragmentTransaction fragmentTransaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.content_frame,new GroupChatFragment(holder.groupType.getText().toString()
                        ,groupList.get(position).get("group_name"),groupList.get(position).get("group_image"))).addToBackStack(null);
                fragmentTransaction.hide(fragment);
                fragmentTransaction.commit();*/
            }
        });

       /* holder.groupChatLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });*/

        holder.groupChatLayout.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle(" Are you sure you want to delete selected group").add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        new RetrofitBase(context).deleteGroup(sharedPreferences.getString(Global.USER_ID, ""),
                                sharedPreferences.getString(Global.TOKEN, ""),groupList.get(position).get("group_id"));

                       // Log.e("g_id","sd"+groupList.get(position).get("group_id"));


                        groupList.remove(position);

                        Global.Group_list.remove(position);
                        notifyDataSetChanged();
                        return true;
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return groupList.size();
}

    public class MyViewHolder extends RecyclerView.ViewHolder   {

        ImageView groupImage;
        TextView groupName,groupType,date,chatCount;
        LinearLayout groupChatLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            groupImage=(ImageView)itemView.findViewById(R.id.group_image);
            groupName=(TextView)itemView.findViewById(R.id.group_name);
            groupType=(TextView)itemView.findViewById(R.id.group_type);
            date=(TextView)itemView.findViewById(R.id.group_chat_date);
            chatCount=(TextView)itemView.findViewById(R.id.group_chat_count);
            groupChatLayout=(LinearLayout)itemView.findViewById(R.id.group_chat_layout);

        }
    }


   /* private void loadFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=context.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.commit();


    }*/
}
