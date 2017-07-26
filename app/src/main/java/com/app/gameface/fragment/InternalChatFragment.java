package com.app.gameface.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.os.Message;
import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import static android.content.Context.MODE_PRIVATE;


public class InternalChatFragment extends Fragment {


    ImageView attachment_bt;
    Context context;
    RelativeLayout send_msg_img;
    EditText chat_enter_text;
    SharedPreferences sharedPreferences;
    String group_id;
    ProgressDialog pd;

    RecyclerView internal_chat_recycler_view;
    //-------------------------For chat------------------------------
    Boolean isRunning;
    public  InternalChatFragment(String groupID)
    {


        this.group_id=groupID;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_internal_chat, container, false);

        loadId(view);

        onAttachmentBtClick();

        return view;
    }

    public void loadId(View view)
    {

        context=getActivity();
        sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF,MODE_PRIVATE);

        attachment_bt=(ImageView)view.findViewById(R.id.attachment_bt);

        send_msg_img=(RelativeLayout)view.findViewById(R.id.send_msg_img);

        chat_enter_text=(EditText)view.findViewById(R.id.chat_enter_text);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading....");
        pd.show();

        internal_chat_recycler_view=(RecyclerView)view.findViewById(R.id.internal_chat_recycler_view);

    }

    public void onAttachmentBtClick()
    {

        attachment_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Global.loadFragment(context,new AttachmentFragment());
            }
        });


        send_msg_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("groupID","g Id"+group_id);

                if(chat_enter_text.getText().toString().length()>0) {
                    new RetrofitBase(context).sendTextMessage(sharedPreferences.getString(Global.USER_ID
                            , ""), sharedPreferences.getString(Global.TOKEN
                            , ""), group_id, "T", chat_enter_text.getText().toString());


                    chat_enter_text.setText("");
                }
            }
        });
    }
    // ------------------MY CHANGE---------------------
    Runnable getMessageThread = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            try {
                if (isRunning) {
                    new RetrofitBase(context).getChatHistory(sharedPreferences.getString(Global.USER_ID
                            , ""), sharedPreferences.getString(Global.TOKEN
                            , ""), group_id,"1",pd,internal_chat_recycler_view);
                }
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            getMessageHandler.sendMessage(new Message());

        }
    };

    Handler getMessageHandler = new Handler() {
        public void handleMessage(Message msg) {

            new Thread(null, getMessageThread, "").start();

        };
    };
    // ------------------------------------------------
    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // GlobalConstant.msg_thread=true;

        isRunning = true;

        new Thread(null, getMessageThread, "").start();


    }
    @Override
    public  void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        isRunning = false;

    }

}
