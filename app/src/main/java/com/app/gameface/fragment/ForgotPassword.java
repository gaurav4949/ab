package com.app.gameface.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Validation;
import com.app.gameface.webServices.RetrofitBase;

public class ForgotPassword extends Fragment {


    Button submit;
    EditText emailAddress;
    RelativeLayout mainTitleLayout,billboardAds,returnToLogin;

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forgot_password, container, false);

        loadData(view);     //intialize documents

        onClick();       // handle click listeners

        return view;
    }


    public void loadData(View view)
    {
        context=getActivity();

        submit=(Button)view.findViewById(R.id.submit_bt);

        emailAddress=(EditText) view.findViewById(R.id.input_email_address);

        returnToLogin=(RelativeLayout)view.findViewById(R.id.return_layout);

        /*To hide main title layout in Forgot password screen*/

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.GONE);

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);


    }

    public  void onClick()
    {

        /*on Submit Button Click*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validation validation=new Validation();

                if (validation.validateEmail(context,emailAddress.getText().toString()))
                {


                //    showMessage(context,"Email sent Successfully");
                    new RetrofitBase(context).forgotPasswordResponse(emailAddress.getText().toString());


                }


            }
        });



        /*on Return to Login Click*/

        returnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm=getFragmentManager();
                fm.popBackStack();
/*
                Fragment login=new Login();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.content_frame,login);
                fragmentTransaction.commit();*/


            }
        });




    }

    public  void showMessage(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }

}
