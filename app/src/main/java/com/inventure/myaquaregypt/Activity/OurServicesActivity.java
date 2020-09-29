package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Adapter.OurServicesAdapter;
import com.inventure.myaquaregypt.Model.OurServices.ourServicesObj;
import com.inventure.myaquaregypt.Model.OurServices.ourServicesRes;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class OurServicesActivity extends AppCompatActivity {
    private RecyclerView ourServicesRV;
    private ArrayList<ourServicesObj> ourServicesObjs = new ArrayList<>();
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);

        ourServicesRV = findViewById(R.id.ourServicesRV);
        dialog = myUtils.LoadingDialog(this);
        dialog.show();
        GetData();

    }

    private void GetData() {
        AndroidNetworking.get(ConstantsUrl.OurServices)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        dialog.dismiss();
                        Log.d("OurServicesActivity", response.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ourServicesRes ourServicesRes = gson.fromJson(response.toString(), ourServicesRes.class);
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            ourServicesObjs = ourServicesRes.getOurServices();
                            setData(ourServicesObjs);
                        }



                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog.dismiss();
                        Log.d("OurServicesActivity", anError.toString());

                    }
                });
    }

    private void setData(ArrayList<ourServicesObj> ourServicesObjs) {
        OurServicesAdapter mAdapter = new OurServicesAdapter(OurServicesActivity.this, ourServicesObjs);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ourServicesRV.setLayoutManager(manager);
        ourServicesRV.setAdapter(mAdapter);

    }


}