package com.inventure.myaquaregypt.Model.Favouirtes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 3/2/19.
 */

public class favouriteObjPOJO {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("project_img")
    @Expose
    private String projectImg;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("location")
    @Expose
    private String location;

    /**
     * No args constructor for use in serialization
     *
     */
    public favouriteObjPOJO() {
    }

    /**
     *
     * @param location
     * @param description
     * @param projectImg
     * @param productTitle
     * @param projectName
     * @param productId
     */
    public favouriteObjPOJO(Integer productId, String projectName, String productTitle, String projectImg, String description, String location) {
        super();
        this.productId = productId;
        this.projectName = projectName;
        this.productTitle = productTitle;
        this.projectImg = projectImg;
        this.description = description;
        this.location = location;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
