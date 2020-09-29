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

public class TermsAndPoliciesActivity extends AppCompatActivity {
    private TextView textview;
    //    private AlertDialog dialog1;
    private Dialog dialog1;

    private ScrollView parentOfTermesAndPolicies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_policies);

        myUtils.setLocale(this);
        definitions();

        showDialog();
        Get_Data();
    }


    private void definitions() {

        textview = findViewById(R.id.text_of_terms);
        parentOfTermesAndPolicies = findViewById(R.id.parentOfTermesAndPolicies);


    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

    }


    private void Get_Data() {

        AndroidNetworking.get(ConstantsUrl.termsAndPolicies)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                            dialog1.dismiss();
                        parentOfTermesAndPolicies.setVisibility(View.VISIBLE);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        AboutUsModelObject array = gson.fromJson(response.toString(), AboutUsModelObject.class);
                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            textview.setText(array.getText());

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Toast.makeText(TermsAndPoliciesActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });


    }


}
