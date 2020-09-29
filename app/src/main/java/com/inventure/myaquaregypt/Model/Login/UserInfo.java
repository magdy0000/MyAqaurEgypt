package com.inventure.myaquaregypt.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 2/19/19.
 */

public class UserInfo {
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("phone")
    @Expose
    private String phone;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserInfo() {
    }

    /**
     *
     * @param phone
     * @param username
     * @param email
     * @param token
     * @param userId
     * @param jobTitle
     */
    public UserInfo(Integer userId, String username, String token, String email, String jobTitle, String phone) {
        super();
        this.userId = userId;
        this.username = username;
        this.token = token;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
