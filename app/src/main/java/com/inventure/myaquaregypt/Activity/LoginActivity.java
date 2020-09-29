package com.inventure.myaquaregypt.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    //    gif_final
    @BindView(R.id.enter_email)
    EditText enter_email;
    @BindView(R.id.enter_pass)
    EditText enter_pass;
    @BindView(R.id.loginFB)
    LinearLayout loginFB;

    //socialData
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 123;

    //dialog
//    AlertDialog dialog1;
    Dialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.login);

        printKeyHash();


        myUtils.setLocale(this);

        facebookToken();
        googleToken();
        ButterKnife.bind(this);

//        dialog1 = new SpotsDialog.Builder().setContext(LoginActivity.this).setTheme(R.style.Custom).build();
//        dialog1.setMessage("Please wait.....");
        dialog1 = myUtils.LoadingDialog(this);


    }

    @OnClick(R.id.login)
    public void onLoginPress() {
        String email = enter_email.getText().toString().trim();
        String password = enter_pass.getText().toString().trim();
        ValidationData(email, password);
    }

    private void ValidationData(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            enter_email.setError("Required");
        }
        if (TextUtils.isEmpty(password)) {
            enter_pass.setError("Required");
        } else {
            onLogin(email, password);
        }
    }

    private void onLogin(String email, String password) {
        dialog1.show();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("password", password);
        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.Login)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();

                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);

                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());
                        Log.d("testest", response.toString());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        getToken();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        dialog1.dismiss();
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        Log.d("testest", anError.getErrorCode() + "");

                        if (anError.getErrorCode() != 0) {

                            myUtils.handleError(LoginActivity.this, anError.getErrorBody(), anError.getErrorCode());

                        } else

                            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void regist(View v) {

        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    }

    @OnClick(R.id.loginFB)
    public void onFacebookBT() {

        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));

    }

    @OnClick(R.id.logingoggleplus)
    public void onGoogleBT() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.skip)
    public void skip() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
//        mySharedPreference.setUserOBJ("");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            //Google response
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            int statusCode = result.getStatus().getStatusCode();

        } else {
            //facebook response
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("googleData", account.getEmail() + "," + account.getDisplayName());

            sendDataToRegister(account.getDisplayName(), account.getEmail());
        } catch (ApiException e) {
            Log.d("googleData", "signInResult:failed code=" + e.getStatusCode());

            Toast.makeText(this, "Failed to do Sign In", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendDataToRegister(String name, String mail) {
        socialLoginPOJO object = new socialLoginPOJO(name, mail);

        Intent toregister = new Intent(LoginActivity.this, RegisterActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(ConstantsUrl.userDataBundleKey, object);
        toregister.putExtras(mBundle);
        startActivity(toregister);
        finish();
    }


    private void facebookToken() {


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        Log.d("facebookData", object.toString());
                        try {
                            String name = object.getString("name");


                            Log.d("facebookData", name);

                            sendDataToRegister(name, "");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link");
                request.setParameters(parameters);
                request.executeAsync();

                LoginManager.getInstance().logOut();

            }

            @Override
            public void onCancel() {
                Log.d("FacebookData", "facebook cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FacebookData", error.toString());
                Toast.makeText(LoginActivity.this, "Failed to do Sign In", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void googleToken() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        Log.d ("Token",gso.getServerClientId());
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void printKeyHash() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().
                    getPackageInfo("com.aswany.android.myaquar_eg", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }
    }

    private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        Log.d("Token", token);
//                        Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }
}
