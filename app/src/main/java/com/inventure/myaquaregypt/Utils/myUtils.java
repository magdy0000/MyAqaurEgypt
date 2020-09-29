package com.inventure.myaquaregypt.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inventure.myaquaregypt.Model.Error.ErrorModel;
import com.inventure.myaquaregypt.R;

import java.util.Locale;

/**
 * Created by aswany on 3/3/19.
 */

public class myUtils {
    //QueryValue
    public static final int Residential = 1;
    public static final int Commercial = 2;
    public static final int Medical = 3;
    public static final int HolidayHome = 4;
    public static final int LunchSoon = 5;

    public static void handleError(Context context, String errorRes, int errorStatusCode) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ErrorModel error = gson.fromJson(errorRes, ErrorModel.class);

        Log.d("ErrorRes", errorStatusCode + ":" + error.getMsg() + " : " + error.getCode());
        Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();


    }

    public static Boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }

    public static void setLocale(Context context) {

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());


    }

    public static Dialog LoadingDialog(final Context myContext) {
        final Dialog dialog = new Dialog(myContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        return dialog;
    }
}
