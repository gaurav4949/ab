package com.app.gameface.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.extra.Validation;
import com.app.gameface.webServices.RetrofitBase;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Login extends Fragment {


    Button signUpBt,loginBt;
    TextView forgotPassword,termOfService,privacyPolicy;
    EditText phone,password,code;
    RelativeLayout mainTitleLayout,billboardAds;
    RelativeLayout fbLogin;
    TextView titleText;
    Context context;
    SharedPreferences sharedPreferences;
    TextInputLayout input_layout_code;
    LoginButton fb_login_button;
    CallbackManager callbackManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);



        loadData(view);       //intianlize components

        onClick();              //on click Listeners

        fbData();

        return view;


    }

    public void loadData(View view)
    {

      //  sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,Context.MODE_PRIVATE);

        context=getActivity();

        signUpBt=(Button)view.findViewById(R.id.sign_up_bt);

        loginBt=(Button)view.findViewById(R.id.login_bt);

        fbLogin=(RelativeLayout) view.findViewById(R.id.fb_login_bt);

        forgotPassword=(TextView) view.findViewById(R.id.forgot_password_text);

        termOfService=(TextView)view.findViewById(R.id.terms_of_service_txt);

        privacyPolicy=(TextView)view.findViewById(R.id.privacy_policy_txt);

        phone=(EditText)view.findViewById(R.id.input_phone_number);

        password=(EditText)view.findViewById(R.id.input_password);

        code=(EditText)view.findViewById(R.id.input_code) ;
        code.setEnabled(false);
        /*To hide main title layout in login Screen*/

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.GONE);

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);

        titleText=(TextView)mainActivityLayout.findViewById(R.id.title_text);
        titleText.setText("Game Face");   //set title for Login

        fragmentTransaction=getFragmentManager().beginTransaction();

        input_layout_code=(TextInputLayout)view.findViewById(R.id.input_layout_code);
        input_layout_code.setEnabled(false);



        fb_login_button=(LoginButton) view.findViewById(R.id.fb_login_button);
        fb_login_button.setReadPermissions("email");
        fb_login_button.setFragment(this);
    }

    private void fbData()
    {


      /*  if (fb_profile.getFirstName() != null) {
            LoginManager.getInstance().logOut();
        }
*/
        System.out.println("hello");


        //  LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        fb_login_button.setReadPermissions(Arrays.asList("public_profile", "email","user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>()


        {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback()
                        {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response)
                            {
                                System.out.println("fb_data"+response);

                                // profilePicUrl = object.getJSONObject("picture").getJSONObject("data").getString("url");
                                   /* profilePicUrl=   "https://graph.facebook.com/" + AccessToken.getCurrentAccessToken().getUserId() + "/picture?type=large&width=1080";
                                    first_name=object.getString("last_name");
                                    last_name=object.getString("first_name");

                                    Log.e("Details",profilePicUrl+" "+first_name+" "+last_name+" "+email);

                                    Picasso.with(getActivity()).load(profilePicUrl).into(image);*/
                                try {
                                    new RetrofitBase(getActivity()).FbLogin(object.getString("email"),
                                            object.getString("first_name")+" "+object.getString("last_name"),
                                            object.getString("id"),"123","http://graph.facebook.com/"+object.getString("id")+"/picture?type=square"
                                    );


                                    Log.e("email",object.getString("email"));
                                    Log.e("first_name",object.getString("first_name")+" "+object.getString("last_name"));
                                    Log.e("id",object.getString("id"));
                                    Log.e("123","123");
                                    Log.e("url","http://graph.facebook.com/"+object.getString("id")+"/picture?type=square");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                  /*  URL url=new URL(profilePicUrl);*/
                                   /* Bitmap bmp= BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                    image.setImageBitmap(bmp);*/
                            }
                        });
                Bundle bundle=new Bundle();
                bundle.putString("fields", "id,last_name,first_name,email,picture,location");
                request.setParameters(bundle);
                request.executeAsync();



            }

            @Override
            public void onCancel() {


                Log.e("Login onCancel","Login onCancel");
            }

            @Override
            public void onError(FacebookException error) {

                Log.e("Login onError","Login onError");
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public  void onClick()
    {

       /*On Login Button Click*/

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Validation validation=new Validation();

                if(validation.loginValidation(context,phone.getText().toString(),password.getText().toString()))
                {




                     new RetrofitBase(context).loginApi(phone.getText().toString(),"1",password.getText().toString(),"123");



                    // showMessage(context,"Log in Successful");

                     //loadFragment(new Home());

              }


                //loadFragment(new Home());



            }
        });



        /*On Signup Button Click*/


        signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fragmentTransaction.replace(R.id.content_frame,new SignUp());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });



        /*On FaceBook Button Click*/
        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // showMessage(context,"Login with Fb");
                fb_login_button.performClick();
            }



        });



        termOfService.setOnClickListener(new View.OnClickListener() {
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

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMessage(context,"Forgot Password");

                fragmentTransaction.replace(R.id.content_frame,new ForgotPassword());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

       public  void showMessage(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }

    private void loadFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
       // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}
