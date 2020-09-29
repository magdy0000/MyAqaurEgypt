package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.Model.Login.UserInfo;
import com.inventure.myaquaregypt.Model.Login.userResPOJO;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONObject;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    EditText usernameEdit, phoneEdit, passwordEdit, emailEdit, jopEdit;
    Button submit;
//    private AlertDialog alertDialog;
    private Dialog alertDialog;
    private UserInfo userPOJO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Dialog();
         definitions();
         onClick();



        dataCheck();


    }

    private void Dialog(){


        alertDialog = myUtils.LoadingDialog(this);


    }
    private void definitions(){
        usernameEdit = findViewById(R.id.edit_info_username);
        phoneEdit = findViewById(R.id.edit_info_phone);
        passwordEdit = findViewById(R.id.edit_info_password);
        emailEdit = findViewById(R.id.edit_info_Email);
        jopEdit = findViewById(R.id.edit_info_jopTitle);
        submit = findViewById(R.id.submit_BT);


}

    private void onClick(){

        submit.setOnClickListener(this);
    }

    private void dataCheck() {
        Gson gson = new Gson();
        userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        usernameEdit.setText(userPOJO.getUsername());
        phoneEdit.setText(userPOJO.getPhone());
        emailEdit.setText(userPOJO.getEmail());
        jopEdit.setText(userPOJO.getJobTitle());
//        if (!Objects.equals(userPOJO.getEmail(),"")) {
//            getEditData(usernameEdit, phoneEdit, emailEdit, jopEdit, passwordEdit);
//        }
    }


    private void getEditData(EditText usernameEditET, EditText phoneEditET, EditText emailEditET, EditText jopEditET, EditText passwordEditET) {

        String userName, userPhone, userEmail, userJob, userPassword;

        userEmail = emailEditET.getText().toString();
        userName = usernameEditET.getText().toString();
        userPhone = phoneEditET.getText().toString();
        userJob = jopEditET.getText().toString();
        userPassword = passwordEditET.getText().toString();

        if (TextUtils.isEmpty(userName))
            usernameEditET.setError("Required");
        else if (TextUtils.isEmpty(userPhone))
            phoneEditET.setError("Required");
        else if (TextUtils.isEmpty(userEmail))
            emailEditET.setError("Required");
//        else if (TextUtils.isEmpty(userPassword))
//            passwordEditET.setError("Required");
//        else if (TextUtils.isEmpty(userJob))
//            jopEditET.setError("Required");
        else
            updateData(userName, userPhone, userEmail, userPassword, userJob);


    }

    private void updateData(String userName, String userPhone, String userEmail, String userPassword, String userJob) {
        alertDialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", userPOJO.getUserId());
            jsonObject.put("user_name", userName);
            jsonObject.put("email", userEmail);
            jsonObject.put("phone", userPhone);
            jsonObject.put("password", userPassword);
            jsonObject.put("job_title", userJob);
        } catch (Exception e) {
            alertDialog.dismiss();
            e.printStackTrace();
        }
        Log.d("Update_Data", jsonObject.toString());

        AndroidNetworking.post(ConstantsUrl.UpdateUser)
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        alertDialog.dismiss();
                        Log.d("Response", response.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);

                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        finish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        alertDialog.dismiss();
                        myUtils.handleError(EditProfileActivity.this, anError.getErrorBody(), anError.getErrorCode());
                    }
                });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.submit_BT:
                if (!Objects.equals(userPOJO.getEmail(), "")) {
                    getEditData(usernameEdit, phoneEdit, emailEdit, jopEdit, passwordEdit);
                }
        }
    }
    @Override
    public void onBackPressed() {

        finish();

    }
}
