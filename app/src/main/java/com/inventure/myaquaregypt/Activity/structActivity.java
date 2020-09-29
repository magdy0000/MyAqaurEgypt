package com.inventure.myaquaregypt.Activity;

import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.inventure.myaquaregypt.Adapter.customerImageViewPagerAdapter;
import com.inventure.myaquaregypt.InternalStorage.Session;
import com.inventure.myaquaregypt.R;
import com.ortiz.touchview.TouchImageView;

import java.util.List;


public class structActivity extends AppCompatActivity {

    TouchImageView touchImageView;
    ViewPager viewPager;
    customerImageViewPagerAdapter adapter;
    List<String> protoTypeImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct);


        viewPager = findViewById(R.id.viewPagerStructure);
        try {
            if (protoTypeImages.size()>0) {
                protoTypeImages = Session.getInstance().getStructureImages();
                adapter = new customerImageViewPagerAdapter(this, protoTypeImages);
                viewPager.setAdapter(adapter);
                for (int x = 0; x < protoTypeImages.size(); x++) {
                    Log.d("Sliders", protoTypeImages.get(x));
                }
            }
        }catch (Exception e){e.printStackTrace();}

//        touchImageView.setMaxZoom(3);
//        touchImageView.setMinZoom(1);

    }


}