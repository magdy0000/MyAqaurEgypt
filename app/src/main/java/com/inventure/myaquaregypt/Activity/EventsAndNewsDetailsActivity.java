package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.Model.EventandNewsDetailsModel.Model_array_of_Eventandnews;
import com.inventure.myaquaregypt.Model.EventandNewsDetailsModel.Model_eventandnews_details;
import com.inventure.myaquaregypt.Model.Login.UserInfo;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class EventsAndNewsDetailsActivity extends AppCompatActivity {

    private Button Attend_btn;
    private SliderLayout Event_slider;
    //    private AlertDialog dialog1;
    private Dialog dialog1;
    private TextView event_description, titile, event_devolper;

    private Button share_btn, phone_btn;
    private RelativeLayout parentOfEventAndNewDetails;

    List<String> urlimage = new ArrayList<>();

    private ArrayList<Model_eventandnews_details> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_and_news_details);
        myUtils.setLocale(this);
        definitions();

        showDialog();


        GetCategoryData(NewsAndEvents.id_event);

        onClick();


    }

    private void showDialog() {

        dialog1 = myUtils.LoadingDialog(this);
        dialog1.show();


    }

    private void definitions() {
        Event_slider = findViewById(R.id.event_Slider);
        event_description = findViewById(R.id.description_event);
        event_devolper = findViewById(R.id.event_devolepor);
        titile = findViewById(R.id.Event_name);
        phone_btn = findViewById(R.id.call_event);
        parentOfEventAndNewDetails = findViewById(R.id.parentOfEventAndNewsDetails);
        share_btn = findViewById(R.id.share_event);
        Attend_btn = findViewById(R.id.Attend);


    }

    private void onClick() {
        Attend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=com.aswany.android.myaquar_eg");
                share.setType("textDes/plain");
                startActivity(share);

            }
        });

        phone_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contact = new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:01033110330"));
                startActivity(contact);

            }
        });


    }

    private void GetCategoryData(int value) {


        AndroidNetworking.get(ConstantsUrl.EventAndNews)
                .addQueryParameter("id", String.valueOf(value))
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

//                        dialog1.dismiss();
                        parentOfEventAndNewDetails.setVisibility(View.VISIBLE);
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        Model_array_of_Eventandnews array = gson.fromJson(response.toString(), Model_array_of_Eventandnews.class);

                        if (dialog1.isShowing()) {
                            dialog1.dismiss();
                            list = array.getProject();

                            for (int i = 0; i < list.get(0).getSlider_images().size(); i++) {
                                String x = list.get(0).getSlider_images().get(i).getImage_url();
                                urlimage.add(x);
                            }

                            DataOfSlider(urlimage);

                            seteventdata(list.get(0).getDescription(), list.get(0).getProject(), list.get(0).getTitle());
                        }


                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(EventsAndNewsDetailsActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                    }
                });
    }

    private void DataOfSlider(List imageUrl) {
        HashMap<String, String> file_maps = new HashMap<String, String>();

        for (int i = 0; i < urlimage.size(); i++) {
            file_maps.put("Phase" + i + 1, imageUrl.get(i).toString());

        }
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            Event_slider.addSlider(textSliderView);
        }
        Event_slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        Event_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        Event_slider.setCustomAnimation(new DescriptionAnimation());
        Event_slider.setDuration(4000);
    }

    private void seteventdata(String dec, String event_dev, String name) {

        event_description.setText(dec);
        event_devolper.setText(event_dev);
        titile.setText(name);


    }


    private void sendEmail() {

        Gson gson = new Gson();
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        String[] TO = {"info@myaquar-eg.com"};
        String recepientEmail = ""; // either set to destination email or leave empty
        String Subject;

        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {


                String Lines = "--------------------------------";
                Subject = "Attending this event: "
                        + list.get(0).getTitle();
                String Content = "Name              : " + userPOJO.getUsername()
                        + "\n" + "Mobile Phone : " + userPOJO.getPhone()
                        + "\n" + "Job Title         : " + userPOJO.getJobTitle();
                String Body = "\n" + "\n" + "\n" + "\n" + "\n" + Lines + "\n" + "\n" + Content + "\n" + "\n" + Lines;
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);

//                Log.d("developer: ", list.get(0).getDeveloper() + "");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, Body);

                startActivity(emailIntent);
            }

        } catch (Exception e) {
            Subject = "Attending this event: "
                    + list.get(0).getTitle();
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, "");

            startActivity(emailIntent);
        }


    }


}
