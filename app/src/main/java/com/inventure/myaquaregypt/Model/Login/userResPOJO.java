package com.inventure.myaquaregypt.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 2/19/19.
 */

public class userResPOJO {
    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    /**
     * No args constructor for use in serialization
     *
     */
    public userResPOJO() {
    }

    /**
     *
     * @param userInfo
     */
    public userResPOJO(UserInfo userInfo) {
        super();
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
