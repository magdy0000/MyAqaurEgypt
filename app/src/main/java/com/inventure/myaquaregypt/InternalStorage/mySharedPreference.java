package com.inventure.myaquaregypt.InternalStorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by aswany on 2/7/19.
 */

public class mySharedPreference {
    //variables
    private static Context mAppContext = null;
    private final static String mySharedPreferenceName = "myAquar";
    private final static String mySharedPreference_userOBJ = "userOBJ";
    private final static String mySharedPreference_userToken = "userToken";


    private mySharedPreference() {
    }
    //methods

    public static void init(Context appContext) {
        mAppContext = appContext;
    }

    private static SharedPreferences getSharedPreferences() {
        return mAppContext.getSharedPreferences(mySharedPreferenceName, Context.MODE_PRIVATE);
    }

    public static void setUserOBJ(String userOBJSTR) {

        Log.d("savedUserOBJ",userOBJSTR);

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(mySharedPreference_userOBJ, userOBJSTR).apply();
    }

    public static String getUserOBJ() {
        return getSharedPreferences().getString(mySharedPreference_userOBJ, "");
    }


    public static void setUserToken(String userToken) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(mySharedPreference_userToken, userToken).apply();
    }
    public static String getUserToken() {
        return getSharedPreferences().getString(mySharedPreference_userToken, "");
    }

}
