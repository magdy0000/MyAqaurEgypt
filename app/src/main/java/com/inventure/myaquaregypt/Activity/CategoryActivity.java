package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Adapter.AdapterForHomeFragment;
import com.inventure.myaquaregypt.Fragments.HomeFragment;
import com.inventure.myaquaregypt.InternalStorage.Session;
import com.inventure.myaquaregypt.Model.HomeApi.ModelArray;
import com.inventure.myaquaregypt.Model.HomeApi.ModelObjects;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    HomeFragment getid = new HomeFragment();
    private RecyclerView mRecyclerView;
    private TextView textOfHeader;
    private ImageView love_behind;
    private AdapterForHomeFragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list = new ArrayList<>();
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout parentOfCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categroy);
        myUtils.setLocale(this);

        definitions();

        showDialog();

        //send id of category from nav to here
        MainActivity data = new MainActivity();
        GetCategoryData(data.idForCategoryOfNav);


        textOfHeader.setText(data.headerOfCategory);


    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();


    }

    private void definitions() {

        mRecyclerView = findViewById(R.id.recyclerView_categry);
        textOfHeader = findViewById(R.id.textOfCategory);

        parentOfCategory = findViewById(R.id.parentOfCategory);


    }


    private void GetCategoryData(String value) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", value);

        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.Category)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfCategory.setVisibility(View.VISIBLE);
//                        dialog1.dismiss();
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        ModelArray array = gson.fromJson(response.toString(), ModelArray.class);

                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            list = array.getProjects();
                            setRecyclerData(list);
                        }


                        mAdapter.setOnItemClickListener(new AdapterForHomeFragment.OnItemClickListener() {

                            @Override
                            public void intent_to_detales(int pos, ImageView imageView) {
                                Session.getInstance().setTypesOfUnitID(String.valueOf(list.get(pos).getProductId()));
                                go_detales(pos, imageView);

//                                getid.id = list.get(pos).getProductId();

                            }

                            @Override
                            public void make_love(int pos, ImageView img) {

                            }
                        });

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(CategoryActivity.this, anError.getErrorBody(), anError.getErrorCode());

                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {

        mAdapter = new AdapterForHomeFragment(CategoryActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(CategoryActivity.this, ProjectdetailsActivity.class);
        startActivity(intent);
    }


}
