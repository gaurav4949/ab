package com.app.gameface.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.gameface.R;
import com.app.gameface.activity.MainActivity;
import com.app.gameface.extra.Global;
import com.app.gameface.fragment.BillBoardAds;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.HashMap;



public class AdsPageAdapter  extends PagerAdapter {

    private Activity activity;
    private ArrayList<HashMap<String,String>> ads_images;
    private int[] imagesArray={R.drawable.dummy_contact_img2,R.drawable.dummy_group_img};

    public AdsPageAdapter(Activity activity,ArrayList<HashMap<String,String>> ads_images){

        this.activity = activity;
        this.ads_images = ads_images;
       // this.namesArray = namesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater inflater = (activity).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.bottom_ads_layout, container, false);
        final ImageView imageView = (ImageView) viewItem.findViewById(R.id.ads);
       // imageView.setImageResource(imagesArray[position]);
        //Log.e("podition",""+position);


        Glide.with(activity).load(ads_images.get(position).get("AdImage"))
               // .thumbnail(0.5f)
               .crossFade()
               .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.home_status.equalsIgnoreCase("home"))
                {
                    Global.loadFragment(activity,new BillBoardAds());
                }
                else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ads_images.get(position).get("web_link")));
                    activity.startActivity(browserIntent);

                  /*  Log.e("sfdf","fsdf");
                    Toast.makeText(activity, "Promotions", Toast.LENGTH_SHORT).show();*/
                }
            }
        });


        container.addView(viewItem);
      //  Log.e("sizze",""+ imagesArray.length);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

       // Log.e("hash",""+ads_images.size());
        return ads_images.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == ((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView((View) object);
    }
}