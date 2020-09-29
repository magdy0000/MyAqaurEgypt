package com.inventure.myaquaregypt.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.inventure.myaquaregypt.R;

public class PanoramaViewActivity extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama_view);


        vrPanoramaView=findViewById(R.id.panorama);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.newphoto);
        vrPanoramaView.loadImageFromBitmap(icon, new VrPanoramaView.Options());
        vrPanoramaView.setInfoButtonEnabled(false);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
    //
    @Override
    protected void onPause() {
        super.onPause();
    }
}
