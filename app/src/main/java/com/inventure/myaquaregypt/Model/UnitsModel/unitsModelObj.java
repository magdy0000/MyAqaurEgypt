package com.inventure.myaquaregypt.Model.UnitsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aswany on 3/31/19.
 */

public class unitsModelObj {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("project_img")
    @Expose
    private String projectImg;
    @SerializedName("location_latitude")
    @Expose
    private String locationLatitude;
    @SerializedName("location_longitude")
    @Expose
    private String locationLongitude;
    @SerializedName("types")
    @Expose
    private ArrayList<unitsModelTypes> types = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public unitsModelObj() {
    }

    /**
     *
     * @param locationLatitude
     * @param projectImg
     * @param description
     * @param productTitle
     * @param locationLongitude
     * @param types
     */
    public unitsModelObj(String description, String productTitle, String projectImg, String locationLatitude, String locationLongitude, ArrayList<unitsModelTypes> types) {
        super();
        this.description = description;
        this.productTitle = productTitle;
        this.projectImg = projectImg;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public ArrayList<unitsModelTypes> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<unitsModelTypes> types) {
        this.types = types;
    }
}
