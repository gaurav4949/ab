package com.app.gameface.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;


public class CreateGroup extends Fragment {


    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout;;
    ImageView topLeftImage,topRightImage;
    TextView titleText;
    FragmentManager fm;
    Context context;
    Button team_sport_group_bt,general_group_bt,fantasy_football_group_bt;
    SharedPreferences sharedPreferences;
    EditText group_name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_group, container, false);


        loadData(view);

        onClick();

        return view;
    }




    public void loadData(View view)
    {

        context=getActivity();
        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,Context.MODE_PRIVATE);

        /*To make title layout visible in Home Screen*/

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);

        topLeftImage=(ImageView)mainActivityLayout.findViewById(R.id.top_left_ic);
        topLeftImage.setImageResource(R.drawable.back);



        titleText=(TextView)mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Create Group");   //set title for Create Group

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);


        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);



        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.GONE);


        team_sport_group_bt=(Button)view.findViewById(R.id.team_sport_group_bt);

        general_group_bt=(Button)view.findViewById(R.id.general_group_bt);

        group_name=(EditText)view.findViewById(R.id.group_name);


        fantasy_football_group_bt=(Button)view.findViewById(R.id.fantasy_football_group_bt);


    }

    public void onClick()
    {

        topLeftIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* loadFragment(new Home());*/
               // FragmentManager fm=getFragmentManager();

               /* for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
*/
               fm=getFragmentManager();
            //    Log.e("count","hre"+fm.getBackStackEntryCount());
                fm.popBackStack();
            }
        });


        team_sport_group_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (group_name.getText().toString().length()<3||
                        group_name.getText().toString().length()>15  )
                {

                    Toast.makeText(getActivity(),"Group name should be between 3 to 15 characters",Toast.LENGTH_LONG).show();
                }
                else {
                    Global.loadFragment(context, new TeamSportGroupDetails(group_name.getText().toString()));
                }

            }
        });


        general_group_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (group_name.getText().toString().length()<3||
                        group_name.getText().toString().length()>15  )
                {

                    Toast.makeText(getActivity(),"Group name should be between 3 to 15 characters",Toast.LENGTH_LONG).show();
                }
                else {
                    new RetrofitBase(getActivity()).addGroup(sharedPreferences.getString(Global.USER_ID, "")
                            , sharedPreferences.getString(Global.TOKEN, ""),group_name.getText().toString()
                    ,"GG","","","","","","","","","","");
                }
            }
        });


        fantasy_football_group_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (group_name.getText().toString().length()<3||
                        group_name.getText().toString().length()>15  )
                {

                    Toast.makeText(getActivity(),"Group name should be between 3 to 15 characters",Toast.LENGTH_LONG).show();
                }
                else {
                    new RetrofitBase(getActivity()).addGroup(sharedPreferences.getString(Global.USER_ID, "")
                            , sharedPreferences.getString(Global.TOKEN, ""),group_name.getText().toString()
                            ,"FFG","","","","","","","","","","");
                }
            }
        });
    }



}
