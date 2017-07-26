package com.app.gameface.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.networkConnectivity.ConnectivityReceiver;
import com.app.gameface.webServices.GetContactResponse;
import com.app.gameface.webServices.RetrofitBase;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static android.support.design.R.id.left;


public class Home extends Fragment {


    ImageView topLeftImage,topRightImage,createNewGroup;
    RelativeLayout myGroups,freeAgency,billBoardAds,mainTitleLayout,topLeftIcLayout,topRightIcLayout;
    TextView titleText;
    Context context;
    LinearLayout homeScreenLayout,internal_ad_layout;
    private OnFragmentInteractionListener mListener;
    SharedPreferences.Editor editor;
    View view;
    SharedPreferences  sharedPreferences;


  /*  private OnFragmentInteractionListener mListener;*/




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        loadData(view);

     //   getPermissionToReadUserContacts();


        onClick();
        mListener.onFragmentHomeInteraction("home");

        Log.e(" home frag"," home frag");

        return view;
    }

    private void loadData(View view) {
        context = getActivity();

        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF, Context.MODE_PRIVATE);
        /*To make title layout visible in Home Screen*/

        final MainActivity mainActivityLayout = (MainActivity) getActivity();

        mainTitleLayout = (RelativeLayout) mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);

        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);

        topLeftIcLayout.setVisibility(View.GONE);



        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);

        topRightIcLayout.setVisibility(View.GONE);



        titleText = (TextView) mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Game Face");   //set title for Home

        createNewGroup = (ImageView) view.findViewById(R.id.create_new_group);

        myGroups = (RelativeLayout) view.findViewById(R.id.my_groups);

        freeAgency = (RelativeLayout) view.findViewById(R.id.free_agency);


        homeScreenLayout = (LinearLayout) view.findViewById(R.id.home_screen);


       billBoardAds = (RelativeLayout) mainActivityLayout.findViewById(R.id.billboar_ads);


        homeScreenLayout.post(new Runnable()
        {
            @Override
            public void run()
            {

             //   int pixel=getActivity().getWindowManager().getDefaultDisplay().getHeight();


                 // int dp =pixel/(int)getResources().getDisplayMetrics().density ;

                int height=homeScreenLayout.getHeight();
                //Log.e("height",""+height);


                RelativeLayout.LayoutParams head_params = (RelativeLayout.LayoutParams)billBoardAds.getLayoutParams();

                head_params.setMargins(0, 0, 0, 0); //substitute parameters for left, top, right, bottom
                head_params.height = height/3;
                billBoardAds.setLayoutParams(head_params);

                Log.e("here","here");


            }
        } );

       billBoardAds.setVisibility(View.VISIBLE);




        editor= getActivity().getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE).edit();
       // contactsApi();


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentHomeInteraction(String s);

    }

    private void  onClick()
    {



            /*On My Group Click*/
            myGroups.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  showMessage(context,"My Groups");


                   if(ConnectivityReceiver.isConnected()) {

                       if(Global.login_status_group_list.equalsIgnoreCase("0"))
                       {
                           new RetrofitBase(getActivity()).getGroupList(sharedPreferences.getString(Global.USER_ID, "")
                                   , sharedPreferences.getString(Global.TOKEN, ""));

                       }
                       else if(Global.login_status_group_list.equalsIgnoreCase("1"))
                            {
                                Global.loadFragment(context, new MyGroup());
                            }

                   }
                   else
                   {
                      showSnackbar(ConnectivityReceiver.isConnected());
                   }

                }
            });


            /*On Create New Group Click*/
            createNewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(ConnectivityReceiver.isConnected()) {

                        if(Global.login_status_group_list.equalsIgnoreCase("0"))
                        {
                            new RetrofitBase(getActivity()).loadGroupList(sharedPreferences.getString(Global.USER_ID, "")
                                    , sharedPreferences.getString(Global.TOKEN, ""));

                        }
                        else if(Global.login_status_group_list.equalsIgnoreCase("1"))
                        {
                            //Global.loadFragment(context, new MyGroup());
                            Global.loadFragment(getActivity(),new CreateGroup());
                        }

                    }
                    else
                    {
                        showSnackbar(ConnectivityReceiver.isConnected());
                    }
                  //  showMessage(context,"Create new Group");



                }
            });

             /*On Free Agency Click*/
            freeAgency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showMessage(context,"Free Agency");
                }
            });

           /* On BillBoards Click*/
          /*  billBoardAds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    showMessage(context,"Billboard Ads");
                }
            });*/


    }

    public  void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public void showSnackbar(boolean isConnected)
    {

        final LinearLayout mainLayout=(LinearLayout)view.findViewById(R.id.home_layout);

        if(!isConnected)
        {

            Snackbar snackbar = Snackbar
                    .make(mainLayout, "No internet Connection!", Snackbar.LENGTH_LONG)
                    /*.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar = Snackbar.make(mainLayout, "No internet Connection!", Snackbar.LENGTH_SHORT);

                            snackbar.show();
                        }
                    })*/;

            snackbar.show();

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

            /*if (Global.contacts.size() > 0) {
                new RetrofitBase(context).getContactResponse(sharedPreferences.getString(Global.USER_ID, "")
                        , sharedPreferences.getString(Global.TOKEN, ""), s.toString());
            }*/
         /*==============================FETCH CONTACTS FROM PHONE=================================*/

        }



    }

    // Identifier for the permission request
    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
   /* public void getPermissionToReadUserContacts() {

         boolean b= ActivityCompat.checkSelfPermission(getActivity()
                 , Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED;
        Log.e("cccc","cccc"+b);
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }
            Log.e("cccc","cccc");

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
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
                contactsApi();

            } else {
                Toast.makeText(context, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
*/

}
