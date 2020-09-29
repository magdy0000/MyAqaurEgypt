package com.inventure.myaquaregypt.Activity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Model.AboutUs.AboutUsModelObject;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;


import org.json.JSONObject;

public class AboutUsActivity extends AppCompatActivity {


    TextView aboutUs;
    ScrollView parentOfAboutUs;
    private Dialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        myUtils.setLocale(this);

          definitions();
          showDialog();
          Get_Data();

    }

    private void showDialog(){

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

    }


    private void definitions(){

        aboutUs = findViewById(R.id.about_us_text);
        parentOfAboutUs = findViewById(R.id.parentOfAboutUs);


    }

    private void Get_Data() {

        AndroidNetworking.get(ConstantsUrl.aboutUs)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parentOfAboutUs.setVisibility(View.VISIBLE);
//                        dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);

                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            aboutUs.setText(array.getText());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Toast.makeText(AboutUsActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }





}
