package com.app.gameface.fragment;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.adapter.ContactsAdapter;
import com.app.gameface.adapter.GroupsAdapter;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import java.util.ArrayList;
import java.util.HashMap;

import static android.support.v4.content.ContextCompat.checkSelfPermission;


public class Contacts extends Fragment {



    RecyclerView contactsRecyclerView;
    ContactsAdapter contactsAdapter;
    Context context;
    ImageView topLeftImage,topRightImage;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout,refresh_bt;


    int[] contactImg={R.drawable.dummy_contact_img1,R.drawable.dummy_contact_img2,R.drawable.dummy_contact_img1,R.drawable.dummy_contact_img2,
            R.drawable.dummy_contact_img1,R.drawable.dummy_contact_img2,R.drawable.dummy_contact_img1,R.drawable.dummy_contact_img2
    ,R.drawable.dummy_contact_img1,R.drawable.dummy_contact_img2};

    String[] contactName={"Cloudia Daniels","Gerald Morvant"
            ,"Cloudia Daniels","Gerald Morvant","Cloudia Daniels","Gerald Morvant",
            "Cloudia Daniels","Gerald Morvant",
            "Cloudia Daniels","Gerald Morvant"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contacts, container, false);



        loadData(view);

        Log.e("login_status",Global.login_status);

        if(Global.login_status.equalsIgnoreCase("0")) {

            getPermissionToReadUserContacts();
        }
        onClick();


        return view;
    }



    public void loadData(View view)
    {
        context=getActivity();

       /* *//*==============================FETCH CONTACTS FROM PHONE=================================*//*


        Cursor cursor=null;


        cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);


        //int contacIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int PhoneNumberIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int PhotoIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);


        ArrayList<HashMap<String,String>> hashMaps=new ArrayList<>() ;
        cursor.moveToFirst();
        if(cursor!=null) {
            do {
                HashMap<String,String> map=new HashMap<>();
                map.put("name",  cursor.getString(nameIndex));
                map.put("number", cursor.getString(PhoneNumberIndex));
                hashMaps.add(map);

            } while (cursor.moveToNext());

        }
        cursor.close();
         *//*==============================FETCH CONTACTS FROM PHONE=================================*/

        contactsRecyclerView = (RecyclerView) view.findViewById(R.id.contacts_recycler_view);
         if(Global.login_status.equalsIgnoreCase("1")) {



             contactsAdapter = new ContactsAdapter(getActivity(), Global.contacts, Global.contacts_response);

             RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

             contactsRecyclerView.setLayoutManager(mLayoutManager);

             contactsRecyclerView.setAdapter(contactsAdapter);
         }

        MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);


        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);
        topLeftImage = (ImageView) mainActivityLayout.findViewById(R.id.top_left_ic);
        topRightImage = (ImageView) mainActivityLayout.findViewById(R.id.top_right_ic);


        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.GONE);




        topLeftImage.setImageResource(R.drawable.back);



        topRightImage.setImageResource(R.drawable.refresh);


        refresh_bt=(RelativeLayout)mainActivityLayout.findViewById(R.id.refresh_bt);
        refresh_bt.setVisibility(View.VISIBLE);



    }

    public void onClick()
    {
        refresh_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Global.contacts_response=new ArrayList<HashMap<String, String>>();
                    contactsApi();

               // Toast.makeText(context,"Refresh",Toast.LENGTH_LONG).show();


            }
        });


    }



    // Identifier for the permission request
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    public void getPermissionToReadUserContacts() {

        boolean b= ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED;

        Global.contacts_response=new ArrayList<>();
        if (b)
        {

            //Log.e("sdfsdfsdfsdf","sadsfsfdfdf");
            if(Global.login_status.equalsIgnoreCase("0"))
            {
                contactsApi();

                Global.login_status="1";
            }



        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {


            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();

                Log.e("cccc","cccc");

                contactsApi();
//                contactsAdapter.notifyDataSetChanged();
                Global.login_status="1";

            } else {
                Toast.makeText(context, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



    public void contactsApi()
    {

        final SharedPreferences sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF, Context.MODE_PRIVATE);
        Log.e("USER_ID",""+sharedPreferences.getString(Global.USER_ID,""));
        Log.e("USER_EMAIL",""+sharedPreferences.getString(Global.USER_EMAIL,""));
        Log.e("USER_NAME",""+sharedPreferences.getString(Global.USER_NAME,""));
        Log.e("TOKEN",""+sharedPreferences.getString(Global.TOKEN,""));
        Log.e("USER_PHONE",""+sharedPreferences.getString(Global.USER_PHONE,""));

         /*==============================FETCH CONTACTS FROM PHONE=================================*/


        Cursor cursor=null;

        StringBuilder s=new StringBuilder();

        cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);


        //int contacIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int nameIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int PhoneNumberIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int PhotoIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);


        Global.contacts=new ArrayList<>() ;
        cursor.moveToFirst();
        // int i=0;
        if(cursor!=null) {
            do {


                HashMap<String,String> map=new HashMap<>();
                map.put("name", cursor.getString(nameIndex));
                map.put("number", cursor.getString(PhoneNumberIndex));
                Global.contacts.add(map);




            } while (cursor.moveToNext());

        }

        cursor.close();

        for (int i=0;i<Global.contacts.size();i++)
        {

            if (i==(Global.contacts.size()-1))
            {

                s.append(Global.contacts.get(i).get("number"));

            }
            else
            {

                s.append(Global.contacts.get(i).get("number"));
                s.append(",");
            }



        }

        Log.e("contacts",""+s);


        if(!sharedPreferences.getString(Global.USER_ID,"").equalsIgnoreCase(""))
        {

            if (Global.contacts.size() > 0) {
                new RetrofitBase(context).getContactResponse(sharedPreferences.getString(Global.USER_ID, "")
                        , sharedPreferences.getString(Global.TOKEN, ""), s.toString(),contactsRecyclerView);
            }
         /*==============================FETCH CONTACTS FROM PHONE=================================*/

        }



    }




}
