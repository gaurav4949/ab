package com.app.gameface.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class TeamSportGroupDetails extends Fragment {

    Spinner spinner_organisation_pin,chooseSportSpinner,snacksScheduleSpinner,
            numberofGamesSpinner,ageGroupSpinner,citySpinner,stateSpinner;
    String[] organisationPinOption={"Yes","No"},
            chooseSport={"Football","Soccer","Basketball","Baseball","Hockey","Softball","Ultimate Frisbee"},
            snacksSchedule= {"Yes","No"},
            numberofgames={"2","3","4","5","6","7","8","9","10","11","12",
                            "13","14","15","16","17"},
            age_group={"U4","U5","U6","U7","U8","U9","U10","U11","U12","U13","U14","U15","U16","U17","U18","U19","Adult"};

    Button create_team_sport_group_bt;

    Set<String> array_list;
    Set<String> array_lis_city;
    ArrayList<String>array_State;
    ArrayList<String>array_city;
    int count=0;
    String groupName,orgranisation,chooseSportValue,snacksScheduleValue,ageGroupValue,stateValue,numberofGamesValue;
    static String organisationPin,organisationName;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout;
    LinearLayout organisation_pin_layout;
    TextView titleText,organisation_pin_text_view;
    Context context;
    SharedPreferences sharedPreferences;
    EditText input_team_name,input_city_name;

    public  TeamSportGroupDetails( String groupName)
    {
        this.groupName=groupName;

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_team_sport_group_details, container, false);

        loadId(view);

        setSpinnerData();

        onClick();

        onBackClick();
       // loadState();
       // loadCity();

        return view;
    }



    public void loadState()
    {

        AssetManager am = getActivity().getAssets();
        array_list=new HashSet<>();
        //array_lis_city=new HashSet<>();
        array_State=new ArrayList<String>();
        try {
            InputStream is = am.open("city_list.txt");
            InputStreamReader inputreader = new InputStreamReader(is);
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(inputreader);
                String line;

                while ((line = br.readLine()) != null) {
                    count++;
                    String arr[]=line.split(",");
                    array_list.add(arr[2]);
/*
                    if(arr[2].equalsIgnoreCase("Florida")){
                        array_lis_city.add(arr[0]);
                        Log.e("hello_city",array_lis_city.toString());
                    }*/

                }

                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }

            Iterator<String> iterator = array_list.iterator();
            while (iterator.hasNext()) {
                array_State.add(iterator.next());
            }

          //  Log.e("hello_count",array_lis_city.size()+"");
            Log.e("hello,",array_State.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCity(String State)
    {


        AssetManager am = getActivity().getAssets();
        array_list=new HashSet<>();
        array_lis_city=new HashSet<>();
        array_city=new ArrayList<String>();
        try {
            InputStream is = am.open("city_list.txt");
            InputStreamReader inputreader = new InputStreamReader(is);
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(inputreader);
                String line;

                while ((line = br.readLine()) != null) {
                    count++;
                    String arr[]=line.split(",");
                    array_list.add(arr[2]);

                    if(arr[2].equalsIgnoreCase(State)){
                        array_lis_city.add(arr[0]);
                       // Log.e("hello_city",array_lis_city.toString());
                    }

                }

                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }

            Iterator<String> iterator = array_lis_city.iterator();
            while (iterator.hasNext()) {
                array_city.add(iterator.next());
            }

            Log.e("hello_count",array_city.size()+"");
           // Log.e("hello,",array_string.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadId(View view)
    {

        context=getActivity();

        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,Context.MODE_PRIVATE);

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);



        titleText=(TextView)mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Team Sport Group");   //set title for Create Group

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);


        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);



        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.GONE);


        chooseSportSpinner=(Spinner)view.findViewById(R.id.spinner_choose_sport);
        snacksScheduleSpinner=(Spinner)view.findViewById(R.id.spinner_snacks_schedule);


        ageGroupSpinner=(Spinner)view.findViewById(R.id.spinner_age_group);

      //  citySpinner=(Spinner)view.findViewById(R.id.spinner_city);

        stateSpinner=(Spinner)view.findViewById(R.id.spinner_state);

        organisation_pin_layout=(LinearLayout)view.findViewById(R.id.organisation_pin_layout);

        organisation_pin_text_view=(TextView)view.findViewById(R.id.organisation_pin_text_view);


        numberofGamesSpinner=(Spinner)view.findViewById(R.id.spinner_no_of_game);

        create_team_sport_group_bt=(Button)view.findViewById(R.id.create_team_sport_group_bt);



        input_team_name=(EditText)view.findViewById(R.id.input_team_name);
        input_city_name=(EditText)view.findViewById(R.id.input_city_name);

    }


    public void setSpinnerData()
    {

        ArrayAdapter<String> chooseSportarrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,chooseSport);

        chooseSportarrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        chooseSportSpinner.setAdapter(chooseSportarrayAdapter);

        chooseSportSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                chooseSportValue=chooseSportSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> snackSchedulearrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,snacksSchedule);

        snackSchedulearrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        snacksScheduleSpinner.setAdapter(snackSchedulearrayAdapter);


        snacksScheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                snacksScheduleValue=snacksScheduleSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter<String> ageGroup=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,age_group);

        ageGroup.setDropDownViewResource(android.R.layout.simple_list_item_1);

        ageGroupSpinner.setAdapter(ageGroup);


        ageGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ageGroupValue=ageGroupSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        ArrayAdapter<String> no_of_games=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,numberofgames);

        no_of_games.setDropDownViewResource(android.R.layout.simple_list_item_1);

        numberofGamesSpinner.setAdapter(no_of_games);

        numberofGamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                numberofGamesValue=numberofGamesSpinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        loadState();
        Log.e("city_Size",""+array_State.size());
        Collections.sort(array_State, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> state=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,array_State);

        state.setDropDownViewResource(android.R.layout.simple_list_item_1);

        stateSpinner.setAdapter(state);


        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                stateValue=numberofGamesSpinner.getItemAtPosition(i).toString();
               /* loadCity(stateSpinner.getItemAtPosition(i).toString());
                Collections.sort(array_city, String.CASE_INSENSITIVE_ORDER);
                ArrayAdapter<String> city=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,array_city);

                city.setDropDownViewResource(android.R.layout.simple_list_item_1);

                citySpinner.setAdapter(city);
*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onClick()
    {
        organisation_pin_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
                dialog.setContentView(R.layout.organisation_pin_dialog);
                TextView yes_bt = (TextView) dialog.findViewById(R.id.yes_bt);
                TextView no_bt = (TextView) dialog.findViewById(R.id.no_bt);
                final TextView cancel_bt = (TextView) dialog.findViewById(R.id.cancel_bt);

                dialog.show();

                cancel_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                yes_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        organisation_pin_text_view.setVisibility(View.VISIBLE);
                        organisation_pin_text_view.setText("Yes");
                        orgranisation="Y";
                        //Global.loadFragment(context,new OrganisationPin());

                        FragmentTransaction fragmentTransaction=((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.add(R.id.content_frame,new OrganisationPin()).addToBackStack(null);
                        fragmentTransaction.hide(TeamSportGroupDetails.this);
                        fragmentTransaction.commit();

                    }

                });


                no_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        organisation_pin_text_view.setVisibility(View.VISIBLE);
                        organisation_pin_text_view.setText("No");
                        orgranisation="N";
                    }
                });


            } });






       /* create_team_sport_group_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(input_team_name.getText().toString().length()<3||
                        input_team_name.getText().toString().length()>15)
                {

                    Toast.makeText(context,"Team name should be between 3 to 15 characters",Toast.LENGTH_SHORT).show();


                }
                else if(organisation_pin_text_view.getText().toString().length()==0)
                    {
                        Toast.makeText(context,"Do you know your organization pin?",Toast.LENGTH_LONG).show();

                    }

                     else if(orgranisation.equalsIgnoreCase("Y"))
                        {

                            if(organisationPin.length()==0)
                            {

                                Toast.makeText(context,"Please enter organization pin!!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            if(organisationName.length()==0)
                            {

                                Toast.makeText(context,"Please enter organization name!!",Toast.LENGTH_SHORT).show();
                            }
                            else if(input_city_name.getText().toString().length()==0)
                            {


                                Toast.makeText(context,"Please enter city!!",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                new RetrofitBase(getActivity()).addGroup(sharedPreferences.getString(Global.USER_ID, "")
                                        , sharedPreferences.getString(Global.TOKEN, ""),groupName
                                        ,"TSG",input_team_name.getText().toString(),chooseSportValue,snacksScheduleValue
                                        ,numberofGamesValue,organisationPin,organisationName,ageGroupValue,input_city_name.getText().toString()
                                        +" "+stateValue,"","");
                            }

                        }
                        else if(orgranisation.equalsIgnoreCase("N"))
                                {

                                    new RetrofitBase(getActivity()).addGroup(sharedPreferences.getString(Global.USER_ID, "")
                                            , sharedPreferences.getString(Global.TOKEN, ""),groupName
                                            ,"TSG",input_team_name.getText().toString(),chooseSportValue,snacksScheduleValue
                                            ,numberofGamesValue,organisationPin,organisationName,ageGroupValue,input_city_name.getText().toString()
                                                    +" "+stateValue,"","");
                                }

            }
        });*/

}

        public void onBackClick() {
        topLeftIcLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });


    }




    }