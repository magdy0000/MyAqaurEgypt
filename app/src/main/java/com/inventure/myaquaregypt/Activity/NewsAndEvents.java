package com.inventure.myaquaregypt.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.inventure.myaquaregypt.Adapter.EventsAndNewsAdapter;
import com.inventure.myaquaregypt.Model.ModelOfNewsAndEvent.ModelArrayOfEventAndNews;
import com.inventure.myaquaregypt.Model.ModelOfNewsAndEvent.ModelOfEventAndNews;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class NewsAndEvents extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ImageView love_behind;
    private EventsAndNewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ModelOfEventAndNews> list = new ArrayList<>();
    //   public static int id;
    public static int id_event;
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private LinearLayout parentOfEventAndNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news);
        myUtils.setLocale(this);

        parentOfEventAndNews = findViewById(R.id.parentOfEventAndNews);
        mRecyclerView = findViewById(R.id.listOfEventsAndNews);

//        dialog1 = new SpotsDialog.Builder().setContext(NewsAndEvents.this).setTheme(R.style.Custom).build();
//        dialog1.setMessage("Please wait.....");
//        dialog1.show();

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

        GetHome_Data();


    }


    private void GetHome_Data() {


        AndroidNetworking.get(ConstantsUrl.Event)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parentOfEventAndNews.setVisibility(View.VISIBLE);
//                        dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        ModelArrayOfEventAndNews array = gson.fromJson(response.toString(), ModelArrayOfEventAndNews.class);
                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            list = array.getProjects();
                            setRecyclerData(list);
                        }

                        mAdapter.setOnItemClickListener(new EventsAndNewsAdapter.OnItemClickListener() {
                            @Override
                            public void intent_to_detales(int pos, RelativeLayout relativeLayout) {
                                go_detales(pos, relativeLayout);
                                id_event = list.get(pos).getProduct_id();
                            }
                        });
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Toast.makeText(NewsAndEvents.this, "connection field", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setRecyclerData(ArrayList<ModelOfEventAndNews> list) {


        mAdapter = new EventsAndNewsAdapter(NewsAndEvents.this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void go_detales(int pos, RelativeLayout relativeLayout) {
        //  Toast.makeText(this, "go to event and news"+pos, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, EventsAndNewsDetailsActivity.class));



    }





}
