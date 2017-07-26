package com.app.gameface.fragment;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.app.gameface.R;
import com.app.gameface.adapter.GalleryAdapter;


public class GalleryFragment extends Fragment {

    RecyclerView galleryRecyclerView;
    GalleryAdapter galleryAdapter;
    LinearLayout cameraLayout,galleryLayout,videoLayout;
    int CAMERA_VIDEO_REQUEST=2;
    int CAMERA_IMAGE_REQUEST=1;
    int GALLERY_IMAGE_REQUEST=3;
    int RESULT_GALLERY=4;
    int GALLERY_REQUEST=5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);

        loadId(view);

        onClick();

        return view;

    }

    public void loadId(View view)
    {

        galleryRecyclerView=(RecyclerView)view.findViewById(R.id.gallery_recycler_view);
        boolean b=ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED
                ;

        if(b)

        {

            galleryAdapter=new GalleryAdapter(getActivity());

            galleryRecyclerView=(RecyclerView)view.findViewById(R.id.gallery_recycler_view);

            RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);

            galleryRecyclerView.setLayoutManager(layoutManager);

            galleryRecyclerView.setAdapter(galleryAdapter);
           /* Intent galleryIntent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            //startActivityForResult(galleryIntent , RESULT_GALLERY );
*/
        }
        else
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
            },GALLERY_REQUEST);

        }




        cameraLayout=(LinearLayout)view.findViewById(R.id.camera_layout);
        galleryLayout=(LinearLayout)view.findViewById(R.id.gallery_layout);
        videoLayout=(LinearLayout)view.findViewById(R.id.video_layout);

    }

    public void onClick()
    {

        cameraLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cameraLayout.setSelected(true);
                galleryLayout.setSelected(false);
                videoLayout.setSelected(false);
                boolean b=ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
                       && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                        ;

                if(b)

                {
                    Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                     startActivity(intent);

                }
                else
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE
                                ,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERA_IMAGE_REQUEST);

                    }
            }
        });


        galleryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cameraLayout.setSelected(false);
                galleryLayout.setSelected(true);
                videoLayout.setSelected(false);
                boolean b=ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED
                        ;

                if(b)

                {
                    Intent galleryIntent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent , RESULT_GALLERY );

                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                    },GALLERY_IMAGE_REQUEST);

                }

            }


        });

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraLayout.setSelected(false);
                galleryLayout.setSelected(false);
                videoLayout.setSelected(true);
                boolean b=ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE )== PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED
                        ;
                if(b)

                {
                    Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);

                }
                else
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE},CAMERA_VIDEO_REQUEST);

                }



            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == CAMERA_IMAGE_REQUEST) {
            Log.e("CAMERA_IMAGE_REQUEST",""+CAMERA_IMAGE_REQUEST);

            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&grantResults[2] == PackageManager.PERMISSION_GRANTED ) {
                Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);




            } else {
                Toast.makeText(getActivity(), "Please grant permission to capture image", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CAMERA_VIDEO_REQUEST) {
            Log.e("CAMERA_IMAGE_REQUEST",""+CAMERA_VIDEO_REQUEST);
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&grantResults[2] == PackageManager.PERMISSION_GRANTED ) {
                Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent("android.media.action.VIDEO_CAPTURE");
                startActivity(intent);




            } else {
                Toast.makeText(getActivity(), "Please grant permission to capture video", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        if (requestCode == GALLERY_IMAGE_REQUEST) {
            Log.e("CAMERA_IMAGE_REQUEST",""+GALLERY_IMAGE_REQUEST);
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


        if (requestCode == GALLERY_REQUEST) {
            Log.e("CAMERA_IMAGE_REQUEST",""+GALLERY_IMAGE_REQUEST);
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED  ) {
                Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_SHORT).show();

                galleryAdapter=new GalleryAdapter(getActivity());



                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);

                galleryRecyclerView.setLayoutManager(layoutManager);

                galleryRecyclerView.setAdapter(galleryAdapter);
            /*    Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent , RESULT_GALLERY );
*/


            } else {
                Toast.makeText(getActivity(), "Please grant permission to access gallery", Toast.LENGTH_SHORT).show();
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
