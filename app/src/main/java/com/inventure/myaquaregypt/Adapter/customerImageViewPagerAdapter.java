package com.inventure.myaquaregypt.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.inventure.myaquaregypt.R;
import com.ortiz.touchview.TouchImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswany on 3/17/19.
 */

public class customerImageViewPagerAdapter extends PagerAdapter {

    Context myContext;
    List<String> images = new ArrayList<>();
    LayoutInflater layoutInflater;

    public customerImageViewPagerAdapter(Context myContext, List<String> images) {
        this.myContext = myContext;
        this.images = images;
        layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.item_zoom_image, container, false);
        TouchImageView touchImageView = view.findViewById(R.id.touchImageStructure);
        Glide.with(myContext).load(images.get(position)).into(touchImageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
