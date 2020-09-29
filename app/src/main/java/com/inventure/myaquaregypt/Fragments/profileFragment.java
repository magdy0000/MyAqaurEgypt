package com.inventure.myaquaregypt.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.inventure.myaquaregypt.Activity.EditProfileActivity;
import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.Model.Login.UserInfo;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.myUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {

    private Fragment fragment;



    @BindView(R.id.profile_photo)
    ImageView profile_photo;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.jobTitle)
    TextView job_title;
    @BindView(R.id.job_title)
    TextView job_titleBar;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.mobile)
    TextView mobile;


    @BindView(R.id.jobTitle_linear)
    LinearLayout jobTitle_linear;
    @BindView(R.id.username_linear)
    LinearLayout username_linear;
    @BindView(R.id.email_linear)
    LinearLayout email_linear;
    @BindView(R.id.mobile_linear)
    LinearLayout mobile_linear;

    private UserInfo userPOJO;
    private Dialog dialog;
    //dialog
    private AlertDialog alertDialog;


    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        myUtils.setLocale(getActivity());

        ButterKnife.bind(this, view);

        dataCheck();
//        alertDialog = new SpotsDialog.Builder().setContext(getActivity()).setTheme(R.style.Custom).build();
//        alertDialog.setMessage("Update information .....");
        dialog = myUtils.LoadingDialog(getActivity());

        TextView edit = view.findViewById(R.id.edit_btn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });


        return view;

    }

    private void dataCheck() {
        Gson gson = new Gson();
        userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);

        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {

                Glide.with(getActivity()).load(userPOJO.getToken()).into(profile_photo);
                user_name.setText(userPOJO.getUsername());
                username.setText(userPOJO.getUsername());
                job_title.setText(userPOJO.getJobTitle());
                email.setText(userPOJO.getEmail());
                mobile.setText(userPOJO.getPhone());
                job_titleBar.setText(userPOJO.getJobTitle());
            }

        } catch (Exception e) {
            user_name.setText("Please Sign In First");
            user_name.setTextColor(getResources().getColor(R.color.Red));
            onVisibleText(jobTitle_linear, username_linear, email_linear, mobile_linear, job_titleBar);
        }
    }

    private void onVisibleText(LinearLayout username, LinearLayout job_title, LinearLayout email, LinearLayout mobile, TextView job_titleBar) {
        username.setVisibility(View.GONE);
        job_title.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        mobile.setVisibility(View.GONE);
        job_titleBar.setVisibility(View.GONE);
    }



    @Override
    public void onResume() {
        super.onResume();
        dataCheck();
    }




}
