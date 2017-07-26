package com.app.gameface.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Validation;
import com.app.gameface.webServices.RetrofitBase;


public class SignUp extends Fragment {

    EditText userName,phoneNumber,email,password,code;
    Button signUpBt,logInBt;
    TextView termsOfService,privacyPolicy;
    Context context;
    TextInputLayout input_layout_code;


    RelativeLayout mainTitleLayout,billboardAds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);

        loadData(view);       //intianlize components

        onClick();              //on click Listeners


        return view;
    }

    public void loadData(View view)
    {
        context=getActivity();

        userName=(EditText)view.findViewById(R.id.input_username);

        phoneNumber=(EditText)view.findViewById(R.id.input_phone_number);

        email=(EditText)view.findViewById(R.id.input_email);

        password=(EditText)view.findViewById(R.id.input_password);

        signUpBt=(Button)view.findViewById(R.id.sign_up_bt);

        logInBt=(Button)view.findViewById(R.id.login_bt);

        termsOfService=(TextView)view.findViewById(R.id.terms_of_service_txt);

        privacyPolicy=(TextView)view.findViewById(R.id.privacy_policy_txt);
        code=(EditText)view.findViewById(R.id.input_code) ;
        code.setEnabled(false);

         /*To hide main title layout in SignUp*/

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.GONE);

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);

        input_layout_code=(TextInputLayout)view.findViewById(R.id.input_layout_code);
        input_layout_code.setEnabled(false);



    }

    public void onClick()
    {
        /*On Login Button Click*/

        logInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm=getFragmentManager();
                fm.popBackStack();

              /*  Fragment login=new Login();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,login);
                fragmentTransaction.commit();*/


            }
        });



        /*On Signup Button Click*/


        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Validation validation=new Validation();

                if(validation.signUpValidation(context,email.getText().toString(),
                        password.getText().toString(),userName.getText().toString()
                ,phoneNumber.getText().toString()))
                {

                   new RetrofitBase(context).SignUpApi(userName.getText().toString().trim(),
                           email.getText().toString().trim(),
                           password.getText().toString(),phoneNumber.getText().toString(),"1","1234"
                           );



                }

              //  Global.loadFragment(getActivity(),new Home());


            }
        });




        termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMessage(context,"Terms of Service");

            }
        });


        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMessage(context,"Privacy Policy");
            }
        });


    }


    public  void showMessage(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }

}
