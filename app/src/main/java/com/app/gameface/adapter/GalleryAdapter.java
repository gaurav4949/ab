package com.app.gameface.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gameface.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ajit on 7/18/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {


    Context context;
    ArrayList<String> images=new ArrayList<>();


    public GalleryAdapter(Activity context)
    {
        this.context=context;
        images=getAllShownImagesPath(context);
       // Log.e("Total Images",""+images.size());
    }

    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_gallery_layout,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, int position) {






//        holder.imageView.setImageURI(Uri.parse(images.get(position)));

        Glide.with(context).load(images.get(position))
                .centerCrop()
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

         imageView=(ImageView)itemView.findViewById(R.id.gallery_image);



        }
    }


    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        int i=0;
        while (cursor.moveToNext() && i<50 ) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
            i++;
        }
        cursor.close();
        return listOfAllImages;
    }
}


