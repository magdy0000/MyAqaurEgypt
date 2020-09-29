package com.inventure.myaquaregypt.Model.UnitsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 3/31/19.
 */

public class unitsModelTypes {

    @SerializedName("product_id")
    @Expose
    private long productId;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("product_title")
    @Expose
    private String productTitle;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("project_img")
    @Expose
    private String projectImg;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("price_label")
    @Expose
    private String priceLabel;

    /**
     * No args constructor for use in serialization
     */
    public unitsModelTypes() {
    }

    /**
     * @param priceLabel
     * @param typeName
     * @param price
     * @param location
     * @param projectImg
     * @param productTitle
     * @param projectName
     * @param productId
     */
    public unitsModelTypes(long productId, String projectName, String productTitle, String typeName, String projectImg, String location, String price, String priceLabel) {
        super();
        this.productId = productId;
        this.projectName = projectName;
        this.productTitle = productTitle;
        this.typeName = typeName;
        this.projectImg = projectImg;
        this.location = location;
        this.price = price;
        this.priceLabel = priceLabel;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }
}
