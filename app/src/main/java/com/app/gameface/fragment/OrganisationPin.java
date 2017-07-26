package com.app.gameface.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;


public class OrganisationPin extends Fragment {


    EditText editText,input_organisation_name;
    LinearLayout linear_layout;
    ImageView pinOne,pinTwo,pinThree,pinFour,pinFive,pinSix;
    RelativeLayout mainTitleLayout,billboardAds,topLeftIcLayout,topRightIcLayout;;
    TextView titleText;
    Context context;
    Button submit_bt,reset_bt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_organisation_pin, container, false);

        loadId(view);
        onClick();

        onBackClick();
        return view;
    }

    public void loadId(View view)
    {

        editText=(EditText)view.findViewById(R.id.input_pin);
        linear_layout=(LinearLayout)view.findViewById(R.id.linear_layout);


        pinOne=(ImageView)view.findViewById(R.id.pin_1);
        pinTwo=(ImageView)view.findViewById(R.id.pin_2);
        pinThree=(ImageView)view.findViewById(R.id.pin_3);
        pinFour=(ImageView)view.findViewById(R.id.pin_4);
        pinFive=(ImageView)view.findViewById(R.id.pin_5);
        pinSix=(ImageView)view.findViewById(R.id.pin_6);

        submit_bt=(Button)view.findViewById(R.id.submit_bt);
        reset_bt=(Button)view.findViewById(R.id.reset_bt);
        input_organisation_name=(EditText)view.findViewById(R.id.input_organisation_name);

        context=getActivity();

        MainActivity mainActivityLayout=(MainActivity)getActivity();

        mainTitleLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.main_title_layout);

        mainTitleLayout.setVisibility(View.VISIBLE);

        titleText=(TextView)mainActivityLayout.findViewById(R.id.title_text);

        titleText.setText("Link Team");   //set title for Create Group

        billboardAds=(RelativeLayout)mainActivityLayout.findViewById(R.id.billboar_ads);

        billboardAds.setVisibility(View.GONE);


        topLeftIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_left_ic_layout);



        topLeftIcLayout.setVisibility(View.VISIBLE);




        topRightIcLayout=(RelativeLayout)mainActivityLayout.findViewById(R.id.top_right_ic_layout);
        topRightIcLayout.setVisibility(View.GONE);

    }

    public void onClick()
    {


        linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setFocusable(true);
                editText.requestFocus();
                InputMethodManager inputMethodManager=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);
            }
        });



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

                if(editText.getText().length()==0)
                {
                    Log.e("length",""+"1");

                    pinOne.setSelected(false);
                    pinTwo.setSelected(false);
                    pinThree.setSelected(false);
                    pinFour.setSelected(false);
                    pinFive.setSelected(false);
                    pinSix.setSelected(false);

                }

                else  if(editText.getText().length()==1)
                {
                    Log.e("length",""+"1");

                    pinOne.setSelected(true);
                    pinTwo.setSelected(false);
                    pinThree.setSelected(false);
                    pinFour.setSelected(false);
                    pinFive.setSelected(false);
                    pinSix.setSelected(false);



                 } else if(editText.getText().length()==2)
                    {

                        Log.e("length",""+"2");
                        pinOne.setSelected(true);
                        pinTwo.setSelected(true);
                        pinThree.setSelected(false);
                        pinFour.setSelected(false);
                        pinFive.setSelected(false);
                        pinSix.setSelected(false);

                    } else if(editText.getText().length()==3)
                        {

                            Log.e("length",""+"3");
                            pinOne.setSelected(true);
                            pinTwo.setSelected(true);
                            pinThree.setSelected(true);
                            pinFour.setSelected(false);
                            pinFive.setSelected(false);
                            pinSix.setSelected(false);

                        } else if(editText.getText().length()==4)
                            {

                                Log.e("length",""+"4");
                                pinOne.setSelected(true);
                                pinTwo.setSelected(true);
                                pinThree.setSelected(true);
                                pinFour.setSelected(true);
                                pinFive.setSelected(false);
                                pinSix.setSelected(false);

                            } if(editText.getText().length()==5)
                                {


                                    Log.e("length",""+"5");
                                    pinOne.setSelected(true);
                                    pinTwo.setSelected(true);
                                    pinThree.setSelected(true);
                                    pinFour.setSelected(true);
                                    pinFive.setSelected(true);
                                    pinSix.setSelected(false);

                                } if(editText.getText().length()==6)
                                     {
                                         Log.e("length",""+"6");
                                         pinOne.setSelected(true);
                                         pinTwo.setSelected(true);
                                         pinThree.setSelected(true);
                                         pinFour.setSelected(true);
                                         pinFive.setSelected(true);
                                         pinSix.setSelected(true);
                                     }


            }



            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        reset_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                pinOne.setSelected(false);
                pinTwo.setSelected(false);
                pinThree.setSelected(false);
                pinFour.setSelected(false);
                pinFive.setSelected(false);
                pinSix.setSelected(false);
                input_organisation_name.setText("");
            }
        });

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().length()==0||editText.getText().toString().length()<6)
                {
                    Toast.makeText(context,"Enter a valid pin",Toast.LENGTH_LONG).show();

                }
                else if (input_organisation_name.getText().toString().length()<3)
                {
                    Toast.makeText(context,"Enter valid organization name",Toast.LENGTH_LONG).show();

                }
                else
                {
                    TeamSportGroupDetails.organisationName=input_organisation_name.getText().toString();
                    TeamSportGroupDetails.organisationPin=editText.getText().toString();

                    FragmentManager fragmentManager=getFragmentManager();
                    fragmentManager.popBackStack();

                }
            }
        });
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
