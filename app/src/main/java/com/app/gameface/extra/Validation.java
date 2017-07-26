package com.app.gameface.extra;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ajit on 6/28/2017.
 */

public class Validation
{

    public static String phonepattern = "[0-9]{10}+";


    public static boolean match(String str)         //for entering characters only
    {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(str);
        boolean bs = ms.matches();

        return bs;

    }

    private boolean isValidEmaillId(String email)   //for entering  valid email
    {

        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }


    //**********************************Login with email************************
    public boolean loginValidation(Context context, String phone, String password)
    {


        if(phone.length()==0 && password.length()==0 )
        {
            String message  = "Please fill all fields";
            showMessage(message,context);
            return false;
        }





        if(phone.length()==0)
        {
           String message  = "Please enter phone number";
            showMessage(message,context);
            return false;

        } else if (!(phone.trim().matches(phonepattern)))

        {
           String message  = "Please enter a valid phone number";

            showMessage(message,context);
            return false;
        }

        if(password.length()==0)
        {
            String message  = "Please enter password";
            showMessage(message,context);
            return false;

        }
        if(password.length()>15||password.length()<6)
        {
            String message  = "Password Length should be between 8 to 15 characters";
            showMessage(message,context);
            return false;
        }

        else
        {
            return true;
        }
    }


    //**********************************Login with email************************
    public boolean signUpValidation(Context context, String email, String password,String username,String phoneNumber)
    {
        String  message;

        if(email.length()==0 && password.length()==0 && username.length()==0 && phoneNumber.length()==0 )
        {
            message  = "Please fill all fields";
            showMessage(message,context);
            return false;
        }

        if(username.length()==0 || username.length()<4 || username.length()>20)
        {	 message  = "Please enter Valid user name";
            showMessage(message,context);
            return false;

        }else if(!match(username))
                {
                    showMessage("Enter characters only in  name field ",context);
                    return false;
                }




        if(phoneNumber.length()==0)
        {
            message  = "Please enter phone number";
            showMessage(message,context);
            return false;

        } else if (!(phoneNumber.trim().matches(phonepattern)))

                {
                 message  = "Please enter a valid phone number";

                showMessage(message,context);
                return false;
                }


        if(email.length()==0)
        {
            message  = "Please enter email address";
            showMessage(message,context);
            return false;

        }

        if(!isValidEmaillId(email.trim()))
        {


            message  = "Please enter a valid email address";
            showMessage(message,context);
            return false;


        }
        if(password.length()==0)
        {
            message  = "Please enter password";
            showMessage(message,context);
            return false;

        }
        if(password.length()>15||password.length()<6)
        {
            message  = "Password Length should be between 8 to 15 characters";
            showMessage(message,context);
            return false;
        }

        else
        {
            return true;
        }


    }

    //**********************************Submit email (Forgot Password)************************

    public boolean validateEmail(Context context, String email)
    {
        String  message;

        if(email.length()==0)
        {
            message  = "Please enter email address";
            showMessage(message,context);
            return false;

        }

        if(!isValidEmaillId(email.trim()))
        {


            message  = "Please enter a valid email address";
            showMessage(message,context);
            return false;

        }

        else
        {
            return true;
        }


    }


    private void showMessage(String message,Context context)
    {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();


    }

}
