package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Adapter.ExpandListAdapter;
import com.inventure.myaquaregypt.InternalStorage.Session;
import com.inventure.myaquaregypt.Model.UnitsModel.unitsModelObj;
import com.inventure.myaquaregypt.Model.UnitsModel.unitsModelRes;
import com.inventure.myaquaregypt.Model.UnitsModel.unitsModelTypes;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by aswany on 3/25/19.
 */

public class ProjectTypesActivity extends AppCompatActivity {
    //    public static int id;
//    private AlertDialog dialog1;
    private Dialog dialog1;
    private ScrollView parent;
    private ImageView units_detailsImg;
    private TextView units_detailsDes_TV,

    units_detailsDes_label_TV, units_line1_label_TV,
            units_line2_label_TV, units_label_TV;
    private Button units_location_BTN;
    private ExpandableListView types_ExpandableList;
    private ImageView arrow;

    private ArrayList<unitsModelObj> unitsModel = new ArrayList<>();
    private ArrayList<unitsModelTypes> Types = new ArrayList<>();
    private List<String> listDataHeader;
    private List<String> listChildDataID;
    private HashMap<String, List<String>> listDataChild;
    private int ProjectID;

    private Button go_youtube;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_types_activity);

        initializations();
        myUtils.setLocale(this);
        showDialog();
        onClick();
        widgetsVisibility(View.GONE);
        getData();

    }

    private void initializations() {
        units_detailsImg = findViewById(R.id.units_detailsImg);
        units_detailsDes_TV = findViewById(R.id.units_detailsDes_TV);
        units_location_BTN = findViewById(R.id.units_location_BTN);
        types_ExpandableList = findViewById(R.id.types_ExpandableList);
        parent = findViewById(R.id.parent_of_projects_type);
        go_youtube = (Button) findViewById(R.id.go_youtube);
        arrow = findViewById(R.id.arrow_of_exband_list);

        units_detailsDes_label_TV = findViewById(R.id.units_detailsDes_label_TV);
        units_line1_label_TV = findViewById(R.id.units_line1_label_TV);
        units_line2_label_TV = findViewById(R.id.units_line2_label_TV);
        units_label_TV = findViewById(R.id.units_label_TV);

    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();

    }


    private void onClick() {

        types_ExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                arrow.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
            }
        });

        types_ExpandableList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                arrow.setImageResource(R.drawable.group_ind);

            }
        });
        go_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCc1Zc_zqnpjfxTnTtiqlC6A?view_as=subscriber"));
                startActivity(intent);

            }
        });


    }

    private void getData() {


        if (!Objects.equals(Session.getInstance().getProjectID(), null)) {
            ProjectID = Session.getInstance().getProjectID();
            initiateData(ProjectID);

        }


    }

    private void initiateData(int id) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.post(ConstantsUrl.NewInits)
                .addJSONObjectBody(object)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        dialog1.dismiss();

                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        Log.d("TestData", "onResponse: " + response.toString());
                        unitsModelRes unitsModelRes = gson.fromJson(response.toString(), unitsModelRes.class);


                        unitsModel = unitsModelRes.getProject();
                        Types = unitsModel.get(0).getTypes();
                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            setData();
                            parent.setVisibility(View.VISIBLE);
                            widgetsVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        parent.setVisibility(View.VISIBLE);
                        Log.d("TestData", "onResponse: " + anError.toString());
                        myUtils.handleError(ProjectTypesActivity.this, anError.getErrorBody(), anError.getErrorCode());
                        Glide.with(ProjectTypesActivity.this).load(R.drawable.no_record_found).into(units_detailsImg);
                        units_detailsDes_TV.setText("This project has no information yet");
                        units_detailsDes_TV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                        units_detailsDes_TV.setTextColor(getResources().getColor(R.color.Red));
                        widgetsVisibility(View.GONE);
                        arrow.setVisibility(View.GONE);
                        go_youtube.setVisibility(View.GONE);

                    }
                });

    }

    private void widgetsVisibility(int Status) {
        units_detailsDes_label_TV.setVisibility(Status);
        units_line1_label_TV.setVisibility(Status);
        units_line2_label_TV.setVisibility(Status);
        units_label_TV.setVisibility(Status);
        units_location_BTN.setVisibility(Status);
    }

    private void setData() {
        units_detailsImg.setScaleType(ImageView.ScaleType.FIT_XY);
        units_detailsDes_TV.setGravity(Gravity.START);
        Glide.with(ProjectTypesActivity.this).load(unitsModel.get(0).getProjectImg()).into(units_detailsImg);
        units_detailsDes_TV.setText(unitsModel.get(0).getDescription());
        prepareListData();
        expandableListView();
        units_location_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationOfCompound();
            }
        });
    }

    private void locationOfCompound() {
        String geoUri = "http://maps.google.com/maps?q=loc:" + unitsModel.get(0).getLocationLatitude() + "," + unitsModel.get(0).getLocationLongitude();
//        String geoUri = "http://maps.google.com/maps?q=loc:" + 30.256 + "," + 31.336;
        Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
        startActivity(map);

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        List<String> listChildData = new ArrayList<>();
        listChildDataID = new ArrayList<>();
        listDataHeader.add("Types: ");
        for (int x = 0; x < Types.size(); x++) {

            listChildData.add(x, Types.get(x).getTypeName());
            listChildDataID.add(x, String.valueOf(Types.get(x).getProductId()));

        }
        listDataChild.put(listDataHeader.get(0), listChildData);

    }

    private void expandableListView() {

        ExpandableListAdapter listAdapter = new ExpandListAdapter(ProjectTypesActivity.this, listDataHeader, listDataChild);
        types_ExpandableList.setAdapter(listAdapter);
        types_ExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setExpandableListViewHeight(parent, groupPosition);
                return false;
            }
        });
        types_ExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String TypeID = listChildDataID.get(childPosition);
                Session.getInstance().setTypesOfUnitID(TypeID);
                startActivity(new Intent(ProjectTypesActivity.this, ProjectdetailsActivity.class));

                return false;
            }
        });

    }

    private void setExpandableListViewHeight(ExpandableListView parent, int groupPosition) {

        ExpandableListAdapter listAdapter = parent.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, parent);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((parent.isGroupExpanded(i)) && (i != groupPosition))
                    || ((!parent.isGroupExpanded(i)) && (i == groupPosition))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            parent);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = parent.getLayoutParams();
        int height = totalHeight
                + (parent.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        parent.setLayoutParams(params);
        parent.requestLayout();
    }

}
