package com.inventure.myaquaregypt.Model.socialLogin;

/**
 * Created by aswany on 2/19/19.
 */


import java.io.Serializable;

public class socialLoginPOJO implements Serializable {

    public String userName;
    public String userEmail;

    public socialLoginPOJO(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
