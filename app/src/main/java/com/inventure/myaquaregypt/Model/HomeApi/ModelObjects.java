package com.inventure.myaquaregypt.Model.HomeApi;


import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelObjects {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("project_img")
    @Expose
    private String projectImg;
    @SerializedName("price")
    @Expose
    @Nullable
    private String price;
    @SerializedName("project_concurency")
    @Expose
    private String Price_label;

    /**
     * No args constructor for use in serialization
     */
    public ModelObjects() {
    }

    /**
     * @param price
     * @param projectImg
     * @param projectName
     * @param productId
     * @param Price_label
     */
    public ModelObjects(Integer productId, String projectName, String projectImg, String price, String Price_label) {
        super();
        this.productId = productId;
        this.projectName = projectName;
        this.projectImg = projectImg;
        this.price = price;
        this.Price_label = Price_label;
    }

    public String getPrice_label() {
        return Price_label;
    }

    public void setPrice_label(String price_label) {
        Price_label = price_label;
    }

    public Integer getProductId() {
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

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
