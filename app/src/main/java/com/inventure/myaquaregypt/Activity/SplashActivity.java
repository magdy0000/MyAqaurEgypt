package com.inventure.myaquaregypt.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mySharedPreference.init(this);

        Timer();
    }


    public void Timer() {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    checkLoggedInUser();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timer();
    }

    private void checkLoggedInUser() {


        if (mySharedPreference.getUserOBJ().equals("")) {
            //user obj not available
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        } else {
            //user obj available
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }


    }
}
