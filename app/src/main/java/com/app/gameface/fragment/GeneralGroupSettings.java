package com.app.gameface.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.extra.Global;
import com.app.gameface.extra.SaveImage;
import com.app.gameface.webServices.RetrofitBase;
import com.app.gameface.webServices.Upload_Image;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class GeneralGroupSettings extends Fragment  {

    LinearLayout done_layout,logout_layout,group_members_layout,change_grp_name_layout,change_user_name_layout;;
    SharedPreferences.Editor editor;
    String group_id,group_image;
    SharedPreferences sharedPreferences;
    ImageView group_img;
    TextView group_name_text,user_name_text;;
    int RESULT_GALLERY=1;
    public GeneralGroupSettings(String group_id,String group_image)
    {

        this.group_id=group_id;
        this.group_image=group_image;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting_fragment2, container, false);

        loadData(view);



        onClick();

        return view;
    }


    public  void loadData(View view)
    {

        sharedPreferences=getActivity().getSharedPreferences(Global.SHARED_PREF,MODE_PRIVATE);

        editor = getActivity().getSharedPreferences(Global.SHARED_PREF, MODE_PRIVATE).edit();

        done_layout=(LinearLayout)view.findViewById(R.id.done_layout);

        logout_layout=(LinearLayout)view.findViewById(R.id.logout_layout);

        group_members_layout=(LinearLayout)view.findViewById(R.id.group_members_layout);

        group_img=(ImageView)view.findViewById(R.id.group_img);

        Glide.with(getActivity()).load(group_image)
                // .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        group_img.setImageDrawable(resource);
                    }
                });

        group_name_text=(TextView)view.findViewById(R.id.group_name_text);

        group_name_text.setText(Global.GROUP_NAME);


        change_grp_name_layout=(LinearLayout)view.findViewById(R.id.change_grp_name_layout);

        change_user_name_layout=(LinearLayout)view.findViewById(R.id.change_user_name_layout);

        user_name_text=(TextView)view.findViewById(R.id.user_name_text);

        user_name_text.setText(sharedPreferences.getString(Global.USER_NAME,""));

    }


    public  void onClick()
    {


        group_members_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            new RetrofitBase(getActivity()).getGroupMembers(sharedPreferences.getString(Global.USER_ID
                    ,""),sharedPreferences.getString(Global.TOKEN
                    ,""),group_id,GeneralGroupSettings.this);

            }
        });


        done_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm=getFragmentManager();
                fm.popBackStack();

            }
        });

        logout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Log.e("Logout","Logout");



                FragmentManager fm=getFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); i++) {
                    fm.popBackStack();
                }
                //fm.popBackStackImmediate();

                //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,new Login());
                fragmentTransaction.commit();




            }
        });
        change_user_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.setContentView(R.layout.change_username_layout);
                TextView update = (TextView) dialog.findViewById(R.id.Update);

                TextView cancel = (TextView) dialog.findViewById(R.id.cancel);
                final EditText enter_name = (EditText) dialog.findViewById(R.id.enter_name);
                dialog.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if (enter_name.getText().toString().length() < 3 || enter_name.getText().toString().length() > 15) {

                            Toast.makeText(getActivity(), "Group name should be between 3 to 15 characters", Toast.LENGTH_SHORT).show();

                        } else {
                            new RetrofitBase(getActivity()).changeName(sharedPreferences.getString(Global.USER_ID
                                    , ""), sharedPreferences.getString(Global.TOKEN
                                    , ""),enter_name.getText().toString() );
                            dialog.dismiss();

                            user_name_text.setText(enter_name.getText().toString());
                            //Global.USER_NAME = enter_name.getText().toString();

                            editor.putString(Global.USER_NAME,enter_name.getText().toString());
                            editor.commit();

                        }


                    }
                });



            }
        });

        change_grp_name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.setContentView(R.layout.change_gname_dialog);
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

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (enter_gname.getText().toString().length() < 3 || enter_gname.getText().toString().length() > 15) {

                            Toast.makeText(getActivity(), "Group name should be between 3 to 15 characters", Toast.LENGTH_SHORT).show();

                        } else {
                            new RetrofitBase(getActivity()).changeGName(sharedPreferences.getString(Global.USER_ID
                                    , ""), sharedPreferences.getString(Global.TOKEN
                                    , ""), group_id, enter_gname.getText().toString());
                            dialog.dismiss();

                            group_name_text.setText(enter_gname.getText().toString());
                            Global.GROUP_NAME = enter_gname.getText().toString();


                        }
                        dialog.dismiss();

                    }
                });




            }

        });

        group_img.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            boolean b= ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED
                    ;

            if(b)

            {

                selectImage();
            }
            else
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                },RESULT_GALLERY);

            }


        }
    });


    }

    public void selectImage()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null)
                    {
                        startActivityForResult(intent, 1);
                    }
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }

                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {


        if (requestCode == RESULT_GALLERY) {
            Log.e("RESULT_GALLERY",""+RESULT_GALLERY);
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED  ) {
                Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent , RESULT_GALLERY );



            } else {
                Toast.makeText(getActivity(), "Please grant permission to access gallery", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }



    }

    Bitmap Bmp1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1)
            {
                Bundle bundle = new Bundle();
                bundle = data.getExtras();
                Bmp1 = (Bitmap) bundle.get("data");
                SaveImage saveImage = new SaveImage(getActivity());
                File f = saveImage.storeImage(Bmp1);

                // group_img.setImageBitmap(Bmp1);
                group_img.setImageURI(Uri.parse(f.getAbsolutePath()));
                Upload_Image up=new Upload_Image(getActivity(),f.getAbsolutePath());
                up.execute(sharedPreferences.getString(Global.USER_ID
                        , ""), sharedPreferences.getString(Global.TOKEN
                        , ""), group_id);
              /*  new RetrofitBase(getActivity()).changeImage(sharedPreferences.getString(Global.USER_ID
                        , ""), sharedPreferences.getString(Global.TOKEN
                        , ""), group_id, f);*/
                //immg.setImageBitmap(Bmp1);
            }


            if (requestCode == 2)
            {

                if(data!=null) {
                    Uri user_p_uri = data.getData();
                    SaveImage saveImage = new SaveImage(getActivity());
                    Bmp1 = saveImage.previewCapturedImage1(user_p_uri);
                    File f = saveImage.storeImage(Bmp1);

                    group_img.setImageURI(Uri.parse(f.getAbsolutePath()));
                    Upload_Image up=new Upload_Image(getActivity(),f.getAbsolutePath());
                    up.execute(sharedPreferences.getString(Global.USER_ID
                            , ""), sharedPreferences.getString(Global.TOKEN
                            , ""), group_id);
                    /* new RetrofitBase(getActivity()).changeImage(sharedPreferences.getString(Global.USER_ID
                            , ""), sharedPreferences.getString(Global.TOKEN
                            , ""), group_id, f);*/
                    // convertedpath1 = f.getPath().toString();
                }
                else
                {
                    /*if (!product_id.equals("")) {
                        // Picasso.with(AddProduct.this).load(convertedpath1).into(add_product_image);

                        Glide.with(AddProduct.this)
                                .load(convertedpath1)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(add_product_image);
                    }*/


                    System.out.println("data"+data);
                    Toast.makeText(getActivity(), "please select product image", Toast.LENGTH_SHORT).show();


                }
            }

        }





    }
}
