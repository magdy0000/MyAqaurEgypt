package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Model.Search.*;
import com.inventure.myaquaregypt.Model.Search.SearchModelObject;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FilterActivity extends AppCompatActivity {
    public RadioGroup rg1, rg2, rg3;
    public RadioButton rb1, rb2, rb3, rb4, rb5, rb6;
    private Spinner maxarea, minarea, minbed, maxbed, location, minbath, maxbath, minprice,
            maxprice, TypesSpinner;

    public static String itemMinArea, itemMaxArea, itemMinBedroom, itemMaxBedroom, itemMinBathroom, itemMaxBathroom, itemMinPrice, itemMaxPrice, itemSearchKey, itemType;
    public static int radioBtn;
    public static String locationOfSpinner;
    public LinearLayout linearTypes;
    private EditText filter_name_ET;
    private RelativeLayout parentOfFilter;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterString;
    ArrayAdapter<String> TypesAdapter;
    List<String> Types;
    List<Long> TypesID;
    //    private AlertDialog dialog1;
    private Dialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        myUtils.setLocale(this);

        definitions();
        showDialog();
        radioButton();
        Get_Data();
        onClickOfSpinners();


    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();


    }

    private void definitions() {

        rg1 = findViewById(R.id.radiogroup1);
        rg3 = findViewById(R.id.radiogroup3);
        rg2 = findViewById(R.id.radiogroup2);
        rb1 = findViewById(R.id.one);
        rb2 = findViewById(R.id.two);
        rb3 = findViewById(R.id.three);
        rb4 = findViewById(R.id.four);
        rb5 = findViewById(R.id.five);
        maxarea = findViewById(R.id.maxAreaSpinner);
        minarea = findViewById(R.id.minAreaSpinner);
        minbed = findViewById(R.id.minBedroomsSpinner);
        maxbed = findViewById(R.id.maxBedroomSpinner);
        location = findViewById(R.id.locationSpinner);
        minbath = findViewById(R.id.minbathroomsSpinner);
        maxbath = findViewById(R.id.maxBathroomsSpinner);
        minprice = findViewById(R.id.minPriceSpinner);
        maxprice = findViewById(R.id.maxPriceSpinner);
        TypesSpinner = findViewById(R.id.TypesSpinner);
        filter_name_ET = findViewById(R.id.filter_name_ET);
        parentOfFilter = findViewById(R.id.parentOfFilter);
        linearTypes = findViewById(R.id.linearTypes);

    }


    private void Get_Data() {


        AndroidNetworking.get(ConstantsUrl.search)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        dialog1.dismiss();
                        parentOfFilter.setVisibility(View.VISIBLE);

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        SearchModelObject array = gson.fromJson(response.toString(), SearchModelObject.class);
//                        Test array = gson.fromJson(response.toString(), Test.class);

                        Log.d("Response:Filter ", array.toString());

                        if (dialog1.isShowing()) {
                            dialog1.dismiss();

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMaxArea());
                            maxarea.setAdapter(adapter);

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMinArea());
                            minarea.setAdapter(adapter);

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMinPrice());
                            minprice.setAdapter(adapter);

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMaxPrice());
                            maxprice.setAdapter(adapter);


                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMinBadrooms());
                            minbed.setAdapter(adapter);


                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMaxBadrooms());
                            maxbed.setAdapter(adapter);

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMinBathrooms());
                            minbath.setAdapter(adapter);

                            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getMaxBathrooms());
                            maxbath.setAdapter(adapter);

                            adapterString = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, array.getLocations());
                            location.setAdapter(adapterString);
                            Types = new ArrayList<>();
                            TypesID = new ArrayList<>();
                            for (Types types : array.getTypes()) {
                                Types.add(types.getType());
                                TypesID.add(types.getTypeId());
                            }
                            TypesAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_simple_text, Types);
                            TypesSpinner.setAdapter(TypesAdapter);

                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(FilterActivity.this, anError.getErrorBody(), anError.getErrorCode());
                        Toast.makeText(FilterActivity.this, "connection field", Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void onClickOfSpinners() {

        minbed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinBedroom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxbed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxBedroom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        minbath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinBathroom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxbath.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxBathroom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxArea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        minarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinArea = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        minprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMinPrice = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        maxprice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                itemMaxPrice = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                locationOfSpinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TypesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemType = TypesID.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void radioButton() {
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtn = myUtils.Residential;
                linearTypes.setVisibility(View.VISIBLE);

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb3.isChecked() || rb5.isChecked()) {
                    rb3.setChecked(false);
                    rb5.setChecked(false);
                }
                rb1.setChecked(true);


            }
        });

        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioBtn = myUtils.LunchSoon;
                linearTypes.setVisibility(View.VISIBLE);

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();


                if (rb3.isChecked() || rb1.isChecked()) {
                    rb3.setChecked(false);
                    rb1.setChecked(false);
                }

                rb5.setChecked(true);


            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioBtn = myUtils.Medical;
                linearTypes.setVisibility(View.VISIBLE);

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb1.isChecked() || rb5.isChecked()) {
                    rb1.setChecked(false);
                    rb5.setChecked(false);
                }
                rb3.setChecked(true);


            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radioBtn = myUtils.Commercial;
                linearTypes.setVisibility(View.VISIBLE);

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();

                if (rb4.isChecked() || rb5.isChecked()) {
                    rb4.setChecked(false);
                    rb5.setChecked(false);
                }
                rb2.setChecked(true);


            }
        });

        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioBtn = myUtils.HolidayHome;
                linearTypes.setVisibility(View.VISIBLE);

                rg1.clearCheck();
                rg2.clearCheck();
                rg3.clearCheck();
                if (rb2.isChecked() || rb5.isChecked()) {
                    rb2.setChecked(false);
                    rb5.setChecked(false);
                }
                rb4.setChecked(true);

            }
        });
    }

    public void search(View view) {
        itemSearchKey = filter_name_ET.getText().toString();

        Log.d("TAGDEMO", itemType);
        Log.d("TAGDEMO", itemSearchKey);
        startActivity(new Intent(this, SearchResultActivity.class));


    }


}