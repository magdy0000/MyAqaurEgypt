package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.InternalStorage.mySharedPreference;
import com.inventure.myaquaregypt.Model.Login.userResPOJO;
import com.inventure.myaquaregypt.Model.socialLogin.socialLoginPOJO;
import com.inventure.myaquaregypt.R;
import com.inventure.myaquaregypt.Utils.ConstantsUrl;
import com.inventure.myaquaregypt.Utils.myUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.inventure.myaquaregypt.Utils.ConstantsUrl.userDataBundleKey;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_username)
    EditText edit_text_username;
    @BindView(R.id.edit_text_phone)
    EditText edit_text_phone;
    @BindView(R.id.edit_text_jopTitle)
    EditText edit_text_jopTitle;
    @BindView(R.id.edit_text_Email)
    EditText edit_text_Email;
    @BindView(R.id.edit_text_password)
    EditText edit_text_password;
    //    userResPOJO resPOJO=new userResPOJO();
//    private AlertDialog dialog1;
    private Dialog dialog1;

    final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        myUtils.setLocale(this);

         showDialog();
        AndroidNetworking.initialize(this);
        getSocialData();

    }

    private void showDialog(){

        dialog1 = myUtils.LoadingDialog(this);


    }


    @OnClick(R.id.registration_BT)
    public void onRegister() {


        String name = edit_text_username.getText().toString().trim();
        String phone = edit_text_phone.getText().toString().trim();
        String email = edit_text_Email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        String jobTitle = edit_text_jopTitle.getText().toString().trim();
        ValidationRegisterData(name, phone, email, password, jobTitle);


    }

    private void ValidationRegisterData(String name, String phone, String email, String password, String jobTitle) {
        if (TextUtils.isEmpty(name)) {
            edit_text_username.setError("Required");
        } else if (TextUtils.isEmpty(phone)) {
            edit_text_phone.setError("Required");
        }
//        else if (TextUtils.isEmpty(jobTitle)) {
//            edit_text_jopTitle.setError("Required");
//        }
        else if (TextUtils.isEmpty(email)) {
            edit_text_Email.setError("Required");
        } else if (!email.matches(EMAIL_PATTERN)) {
            edit_text_Email.setError("example @ example.com ");
        } else if (TextUtils.isEmpty(password)) {
            edit_text_password.setError("Required");
        } else if (password.length() < 8) {
            edit_text_password.setError("Password must be 8 characters");


        } else {

            dialog1.show();
            getToken();
            onRegisterData(name, password, phone, email, jobTitle);
//            Toast.makeText(getApplicationContext(), "hh", Toast.LENGTH_SHORT).show();
        }
    }

    private void getToken() {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w("Token", "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            Token = task.getResult().getToken();

                            Log.d("Token", Token);
//                        Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    private void onRegisterData(String name, String password, String phone, String email, String jobTitle) {
        JSONObject object = new JSONObject();
        try {
            object.put("user_name", name);
            object.put("phone", phone);
            object.put("email", email);
            object.put("password", password);
            object.put("job_title", jobTitle);
            object.put("fb_token", Token);

        } catch (JSONException e) {
            e.getStackTrace();
        }

          AndroidNetworking.post(ConstantsUrl.Registration)
                .addJSONObjectBody(object)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog1.dismiss();


                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);


                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());


                        mySharedPreference.setUserToken(resPOJO.getUserInfo().getToken());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                    }

                    @Override
                    public void onError(ANError anError) {
                        myUtils.handleError(RegisterActivity.this, anError.getErrorBody(), anError.getErrorCode());
                        Log.d("RegisterError", anError.getErrorBody() + "");
                        Log.d("RegisterError", anError.getErrorCode() + "");
                        dialog1.dismiss();
                    }
                });
    }

    private void getSocialData() {
        socialLoginPOJO socialOBJ = (socialLoginPOJO) getIntent().getSerializableExtra(userDataBundleKey);

        if (socialOBJ != null) {
            edit_text_username.setText(socialOBJ.userName);
            edit_text_Email.setText(socialOBJ.userEmail);
        }
    }

}
