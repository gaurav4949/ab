package com.app.gameface.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.app.gameface.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ajit on 7/3/2017.
 */

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.MyViewHolder> {

    ArrayList<HashMap<String,String>> adsImages;
    Fragment fragment;

    public AdsAdapter(Fragment fragment,ArrayList<HashMap<String,String>> adsImages)
    {

        this.adsImages=adsImages;
        this.fragment=fragment;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_layout, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdsAdapter.MyViewHolder holder, int position)
    {
        Glide.with(fragment).load(adsImages.get(position).get("AdImage"))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.adImage.setImageDrawable(resource);
                    }
                });
    }

    @Override
    public int getItemCount()
    {
        return adsImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView adImage;


        private MyViewHolder(View itemView) {
            super(itemView);

            adImage=(ImageView)itemView.findViewById(R.id.ads_image_view);


        }
    }

}