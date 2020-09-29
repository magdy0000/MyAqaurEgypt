package com.inventure.myaquaregypt.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;

public class OurMagazine extends AppCompatActivity {

    WebView OurMagazineWB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_magzine);

        OurMagazineWB = findViewById(R.id.OurMagazineWB);

        OurMagazineWB.getSettings().setJavaScriptEnabled(true);
        OurMagazineWB.setWebViewClient(new WebViewClient());
        OurMagazineWB.loadUrl(ConstantsUrl.Magazine);

    }





}
