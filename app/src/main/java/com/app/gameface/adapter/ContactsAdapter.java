package com.app.gameface.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.webServices.RetrofitBase;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {


        int[] contactImg;
        Context context;
        final SharedPreferences sharedPreferences;

        ArrayList<HashMap<String,String>> contactName;
        ArrayList<HashMap<String,String>> contactNameResponse;

        public ContactsAdapter(Context context, ArrayList<HashMap<String,String>> contactName
        , ArrayList<HashMap<String,String>> contactNameResponse)
        {

            this.contactName=contactName;
            this.contactNameResponse=contactNameResponse;
            this.context=context;
            sharedPreferences=context.getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE);
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout,null);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactsAdapter.MyViewHolder holder, final int position) {

          //  Log.e("position",""+position);
            holder.contactName.setText(contactName.get(position).get("name"));
//            holder.contactImage.setImageResource(contactImg[position]);

            if(Global.contacts_response.get(position).get("status").equalsIgnoreCase("Registered"))
            {
                holder.send_invitation.setVisibility(View.GONE);
                holder.invitation_sent.setVisibility(View.GONE);

            } else if(Global.contacts_response.get(position).get("status").equalsIgnoreCase("Not Registered"))
                    {
                        holder.send_invitation.setVisibility(View.VISIBLE);
                        holder.invitation_sent.setVisibility(View.GONE);
                    }
                    else if(Global.contacts_response.get(position).get("status").equalsIgnoreCase("Invitation Pending"))
                    {
                        holder.invitation_sent.setVisibility(View.VISIBLE);
                        holder.send_invitation.setVisibility(View.GONE);

                    }


            holder.send_invitation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   new RetrofitBase(context).sendInvitationResponse(sharedPreferences.getString(Global.USER_ID,"")
                           , sharedPreferences.getString(Global.TOKEN,""),Global.contacts_response.get(position).get("phone") );

                    String phone=Global.contacts_response.get(position).get("phone");
                    Global.contacts_response.remove(position);
                    HashMap<String,String> map=new HashMap<String, String>();
                    map.put("status","Invitation Pending");
                    map.put("phone",phone);
                    Global.contacts_response.add(position,map);
                    notifyDataSetChanged();

                }
            });


        }

        @Override
        public int getItemCount()
        {
            return Global.contacts_response.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder   {

            ImageView contactImage,invitation_sent;
            TextView contactName,number;
            LinearLayout send_invitation;


            public MyViewHolder(View itemView) {
                super(itemView);

                contactImage=(ImageView)itemView.findViewById(R.id.contact_image);
                contactName=(TextView)itemView.findViewById(R.id.contact_name);
               // number=(TextView)itemView.findViewById(R.id.number);
                send_invitation=(LinearLayout)itemView.findViewById(R.id.send_invitation);
                invitation_sent=(ImageView)itemView.findViewById(R.id.invitation_sent);


            }
        }

}


