package com.app.gameface.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class AssignCoachCaptain extends Fragment {



    ListView contactsListview;
    RelativeLayout back_layout;
    ArrayList<HashMap<String,String>> contacts=new ArrayList<>();
    ArrayList<String> members=new ArrayList<>();
    SharedPreferences sharedPreferences;



    public AssignCoachCaptain( ArrayList<HashMap<String,String>> contacts,ArrayList<String> members)
    {
        this.contacts=contacts;
        this.members=members;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_assign_coach_captain, container, false);

        loadData(view);

        onCancelClick();

        return view;
    }
    public  void loadData(View view)
    {
        sharedPreferences=getActivity().getSharedPreferences(Global.SHARED_PREF,MODE_PRIVATE);
        contactsListview=(ListView)view.findViewById(R.id.contactsListview);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, members);




        contactsListview.setAdapter(arrayAdapter);


        back_layout=(RelativeLayout) view.findViewById(R.id.back_layout);


        contactsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                Log.e("id name",": "+contacts.get(i).get("member_id")+"/"+members.get(i));


                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.setContentView(R.layout.assign_captain_dialog);
                TextView update = (TextView) dialog.findViewById(R.id.Update);

                TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                final EditText enter_gname = (EditText) dialog.findViewById(R.id.enter_gname);
                dialog.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Log.e(" Global.GROUP_ID"," Global.GROUP_ID: "+ Global.GROUP_ID);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                            new RetrofitBase(getActivity()).assignCapt(sharedPreferences.getString(Global.USER_ID
                                    , ""), sharedPreferences.getString(Global.TOKEN
                                    , ""), Global.GROUP_ID,contacts.get(i).get("member_id") );
                            dialog.dismiss();



                        }



                });


            }
        });




    }


    public  void onCancelClick()
    {
        back_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.popBackStack();

            }
        });

    }

}
