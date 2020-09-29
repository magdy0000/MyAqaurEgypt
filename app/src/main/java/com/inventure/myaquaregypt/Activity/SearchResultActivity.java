package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class SearchResultActivity extends AppCompatActivity {

    private HomeFragment getid = new HomeFragment();
    private RecyclerView mRecyclerView;
    private AdapterForHomeFragment mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelObjects> list = new ArrayList<>();
    private String categoryId;
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout parentOfSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        myUtils.setLocale(this);

       definitions();
       showDialog();
       cheakIfDataIsAll();


       GetCategoryData(categoryId, FilterActivity.itemMaxPrice, FilterActivity.itemMinPrice, FilterActivity.itemMaxArea,
                FilterActivity.itemMinArea, FilterActivity.itemMaxBedroom, FilterActivity.itemMinBedroom, FilterActivity.itemMaxBathroom,
                FilterActivity.itemMinBathroom, FilterActivity.locationOfSpinner, FilterActivity.itemType, FilterActivity.itemSearchKey
        );


    }
    private void showDialog(){

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

    }


    private void definitions(){

        parentOfSearchResult = findViewById(R.id.parentOfSearchResult);
        mRecyclerView = findViewById(R.id.recyclerView_search_result);
        categoryId = String.valueOf(FilterActivity.radioBtn);



    }

    private void GetCategoryData(String category, String maxPrice, String minPrice, String maxArea, String minArea, String maxBedrooms,
                                 String minBedrooms, String maxBathrooms, String minBathrooms, String locations,
                                 String itemType, String itemSearchKey) {

        JSONObject object = new JSONObject();
        try {
            object.put("category_id", category);
            object.put("type", itemType);
            object.put("keywords", itemSearchKey);
            object.put("max_price", maxPrice);
            object.put("min_price", minPrice);
            object.put("max_area", maxArea);
            object.put("min_area", minArea);
            object.put("max_badrooms", maxBedrooms);
            object.put("min_badrooms", minBedrooms);
            object.put("max_bathrooms", maxBathrooms);
            object.put("min_bathrooms", minBathrooms);
            object.put("locations", locations);
            Log.d("SearchDataObj", object.toString());


        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.SearchResult)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfSearchResult.setVisibility(View.VISIBLE);
//                        dialog1.dismiss();
                        Log.d("SearchData", response.toString());
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


                                Session.getInstance().setProjectID(list.get(pos).getProductId());
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
                        Toast.makeText(SearchResultActivity.this, "No result", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                        myUtils.handleError(SearchResultActivity.this, anError.getErrorBody(), anError.getErrorCode());
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelObjects> list) {

        mAdapter = new AdapterForHomeFragment(SearchResultActivity.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void go_detales(int pos, ImageView img) {
        Intent intent = new Intent(SearchResultActivity.this, ProjectTypesActivity.class);
        startActivity(intent);

    }


    private void cheakIfDataIsAll() {


        // why using try and catch ?    because when you intent to this activity and back by backbutton to search activity
        //  and int here again crash occurs

        try {
            if (categoryId.equals("0")) {
                categoryId = "";
            }

            if (FilterActivity.itemMaxPrice.equals("all")) {
                FilterActivity.itemMaxPrice = "";

            }
            if (FilterActivity.itemMinPrice.equals("all")) {
                FilterActivity.itemMinPrice = "";

            }
            if (FilterActivity.itemMinPrice.equals("all")) {
                FilterActivity.itemMinPrice = "";

            }
            if (FilterActivity.itemMaxArea.equals("all")) {
                FilterActivity.itemMaxArea = "";

            } else if (FilterActivity.itemMinArea.equals("all")) {
                FilterActivity.itemMinArea = "";

            }
            if (FilterActivity.itemMaxBedroom.equals("all")) {
                FilterActivity.itemMaxBedroom = "";

            }
            if (FilterActivity.itemMinBedroom.equals("all")) {
                FilterActivity.itemMinBedroom = "";

            }
            if (FilterActivity.itemMaxBathroom.equals("all")) {
                FilterActivity.itemMaxBathroom = "";

            }
            if (FilterActivity.itemMinBathroom.equals("all")) {
                FilterActivity.itemMinBathroom = "";

            }
            if (FilterActivity.locationOfSpinner.equals("all")) {
                FilterActivity.locationOfSpinner = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
