package com.app.gameface.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import static android.content.Context.MODE_PRIVATE;

public class GroupChatFragment extends Fragment {

    RelativeLayout billboardAds,mainTitleLayout;
    LinearLayout week_layout,backButton,group_settings,delete_bt,editText;
    Context context;
    TabLayout chatTabLayout;
    String groupType,groupName,groupImage,groupId;
    TextView group_name,matchup;
    View view;
    ImageView group_dp;

    SharedPreferences sharedPreferences;

    public GroupChatFragment(String groupType,String groupName,String groupImage,String groupId)
    {


        this.groupType=groupType;
        this.groupName=groupName;
        this.groupImage=groupImage;
        this.groupId=groupId;


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        group_name.setText( Global.GROUP_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_group_chat, container, false);



        loadData(view);

        onClick();

        onGroupSettingsClick();
        onWeekLayoutClick();

        return view;
    }

    public void loadData(View view)
    {

        context=getActivity();
        backButton=(LinearLayout)view.findViewById(R.id.back_bt);

        sharedPreferences=getActivity().getSharedPreferences(Global.SHARED_PREF,MODE_PRIVATE);

        group_name=(TextView)view.findViewById(R.id.group_name);

        group_name.setVisibility(View.VISIBLE);
        group_name.setText( Global.GROUP_NAME);


        chatTabLayout=(TabLayout)view.findViewById(R.id.chat_tabLayout);

        group_dp=(ImageView)view.findViewById(R.id.group_dp) ;
        Glide.with(context).load(groupImage)
                // .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        group_dp.setImageDrawable(resource);
                    }
                });

           /* for  football fantasy Schedule internal fragment*/
        week_layout=(LinearLayout)view.findViewById(R.id.week_layout);
        week_layout.setVisibility(View.GONE);
        /* for  football fantasy Schedule internal fragment*/

        /* for  football fantasy matchup internal fragment*/
        matchup=(TextView)view.findViewById(R.id.matchup);
        matchup.setVisibility(View.GONE);
         /* for football fantasy matchupinternal fragment*/

        group_settings=(LinearLayout)view.findViewById(R.id.group_settings);
        matchup.setVisibility(View.GONE);


        delete_bt=(LinearLayout)view.findViewById(R.id.delete_bt);
        delete_bt.setVisibility(View.GONE);


        editText=(LinearLayout)view.findViewById(R.id.edit_text);
        editText.setVisibility(View.GONE);


        loadFragment(new InternalChatFragment(Global.GROUP_ID));

        if(groupType.equalsIgnoreCase("General Group"))
        {

            chatTabLayout.setVisibility(View.GONE);

        }
        else if(groupType.equalsIgnoreCase("Football Fantasy Group"))
        {
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Members"));
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Schedule"));
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Matchups"));


        } else if(groupType.equalsIgnoreCase("Team Sport Group"))
        {
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Members"));
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Schedule"));
            chatTabLayout.addTab(chatTabLayout.newTab().setText("Clipboard"));

        }



        chatTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.GONE);

        billboardAds = (RelativeLayout) mainActivityLayout.findViewById(R.id.billboar_ads);
      /*  billboardAds.setBackgroundResource(R.drawable.dummy_ad);*/

       /* RelativeLayout.LayoutParams head_params = (RelativeLayout.LayoutParams)billboardAds.getLayoutParams();

        head_params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
        head_params.height = 150;
        billboardAds.setLayoutParams(head_params);*/

        billboardAds.setVisibility(View.VISIBLE);

    }

    public void onClick()
    {




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm=getFragmentManager();
                fm.popBackStack();
            }
        });

        chatTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(groupType.equalsIgnoreCase("Football Fantasy Group"))
                {
                    if(tab.getPosition()==0)
                    {

                        // showMessage(context,""+0);
                        loadFragment(new InternalChatFragment(Global.GROUP_ID));
                        week_layout.setVisibility(View.GONE);
                        matchup.setVisibility(View.GONE);
                        editText.setVisibility(View.GONE);
                        group_settings.setVisibility(View.VISIBLE);
                        group_name.setVisibility(View.VISIBLE);
                        delete_bt.setVisibility(View.GONE);


                    }
                    if(tab.getPosition()==1)
                    {

                        /// showMessage(context,""+1);
                        loadFragment(new FootballFantasySchedule());
                        week_layout.setVisibility(View.VISIBLE);
                        matchup.setVisibility(View.GONE);
                        group_settings.setVisibility(View.GONE);
                        editText.setVisibility(View.GONE);
                        group_name.setVisibility(View.GONE);
                        delete_bt.setVisibility(View.VISIBLE);

                    }
                    if(tab.getPosition()==2)
                    {

                        // showMessage(context,""+2);
                       // loadFragment(new FootballFantasyMatchups());
                        new RetrofitBase(getActivity()).getFootballMatchups(sharedPreferences.getString(Global.USER_ID
                                ,""),sharedPreferences.getString(Global.TOKEN
                                ,""),Global.GROUP_ID);
                        Log.e("g_id","d"+Global.GROUP_ID);

                        matchup.setVisibility(View.VISIBLE);
                        week_layout.setVisibility(View.GONE);
                        group_settings.setVisibility(View.GONE);
                        editText.setVisibility(View.VISIBLE);
                        group_name.setVisibility(View.GONE);
                        delete_bt.setVisibility(View.GONE);
                    }



                } else if(groupType.equalsIgnoreCase("Team Sport Group"))
                {
                    if(tab.getPosition()==0)
                    {

                        // showMessage(context,""+0);
                        loadFragment(new InternalChatFragment(Global.GROUP_ID));



                    }
                    if(tab.getPosition()==1)
                    {

                        /// showMessage(context,""+1);
                      //  loadFragment(new TeamSupportSchdule());
                        new RetrofitBase(getActivity()).teamSportSchdule(sharedPreferences.getString(Global.USER_ID
                                ,""),sharedPreferences.getString(Global.TOKEN
                                ,""),Global.GROUP_ID);


                    }
                    if(tab.getPosition()==2)
                    {

                        // showMessage(context,""+2);
                        loadFragment(new TeamSupportClipboard());
                    }


                }




            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void loadFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chat_internal_fragment,fragment);
        fragmentTransaction.commit();


    }

    private void loadintenalFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chat_internal_fragment,fragment).addToBackStack(null);
        fragmentTransaction.commit();


    }

    public void onGroupSettingsClick()
    {


        group_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(groupType.equalsIgnoreCase("General Group"))
                {

                   // Global.loadFragment(getActivity(),new GeneralGroupSettings());

                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();

                    fragmentTransaction.add(R.id.content_frame,new GeneralGroupSettings(groupId,groupImage)).addToBackStack(null);
                    fragmentTransaction.hide(GroupChatFragment.this);
                    fragmentTransaction.commit();

                }
                else if(groupType.equalsIgnoreCase("Football Fantasy Group"))
                {

                //    Global.loadFragment(getActivity(),new FootballFantasySettings());

                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.content_frame,new FootballFantasySettings(groupId,groupImage)).addToBackStack(null);
                    fragmentTransaction.hide(GroupChatFragment.this);
                    fragmentTransaction.commit();

                } else if(groupType.equalsIgnoreCase("Team Sport Group"))
                {

                   // Global.loadFragment(getActivity(),new TeamSportSettings());

                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.content_frame,new TeamSportSettings(groupId,groupImage,Global.GROUP_NAME)).addToBackStack(null);
                    fragmentTransaction.hide(GroupChatFragment.this);
                    fragmentTransaction.commit();
                }

            }
        });





    }


    public void onWeekLayoutClick()
    {

        week_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Global.loadFragment(context,new WeekList());


            }
        });

    }



}
