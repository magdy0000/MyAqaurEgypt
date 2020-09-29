package com.inventure.myaquaregypt.Model.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchModelObject {


    @SerializedName("max_price")
    @Expose
    private List<String> maxPrice = null;
    @SerializedName("min_price")
    @Expose
    private List<String> minPrice = null;
    @SerializedName("max_area")
    @Expose
    private List<String> maxArea = null;
    @SerializedName("min_area")
    @Expose
    private List<String> minArea = null;
    @SerializedName("max_badrooms")
    @Expose
    private List<String> maxBadrooms = null;
    @SerializedName("min_badrooms")
    @Expose
    private List<String> minBadrooms = null;
    @SerializedName("max_bathrooms")
    @Expose
    private List<String> maxBathrooms = null;
    @SerializedName("min_bathrooms")
    @Expose
    private List<String> minBathrooms = null;
    @SerializedName("locations")
    @Expose
    private List<String> locations = null;
    @SerializedName("types")
    @Expose
    private List<Types> types = null;

    /**
     * No args constructor for use in serialization
     */
    public SearchModelObject() {
    }

    /**
     * @param maxBathrooms
     * @param locations
     * @param minBathrooms
     * @param maxArea
     * @param minArea
     * @param maxBadrooms
     * @param minBadrooms
     * @param maxPrice
     * @param types
     * @param minPrice
     */
    public SearchModelObject(List<String> maxPrice, List<String> minPrice, List<String> maxArea, List<String> minArea, List<String> maxBadrooms, List<String> minBadrooms, List<String> maxBathrooms, List<String> minBathrooms, List<String> locations, List<Types> types) {
        super();
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.maxArea = maxArea;
        this.minArea = minArea;
        this.maxBadrooms = maxBadrooms;
        this.minBadrooms = minBadrooms;
        this.maxBathrooms = maxBathrooms;
        this.minBathrooms = minBathrooms;
        this.locations = locations;
        this.types = types;
    }

    public List<String> getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(List<String> maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(List<String> minPrice) {
        this.minPrice = minPrice;
    }

    public List<String> getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(List<String> maxArea) {
        this.maxArea = maxArea;
    }

    public List<String> getMinArea() {
        return minArea;
    }

    public void setMinArea(List<String> minArea) {
        this.minArea = minArea;
    }

    public List<String> getMaxBadrooms() {
        return maxBadrooms;
    }

    public void setMaxBadrooms(List<String> maxBadrooms) {
        this.maxBadrooms = maxBadrooms;
    }

    public List<String> getMinBadrooms() {
        return minBadrooms;
    }

    public void setMinBadrooms(List<String> minBadrooms) {
        this.minBadrooms = minBadrooms;
    }

    public List<String> getMaxBathrooms() {
        return maxBathrooms;
    }

    public void setMaxBathrooms(List<String> maxBathrooms) {
        this.maxBathrooms = maxBathrooms;
    }

    public List<String> getMinBathrooms() {
        return minBathrooms;
    }

    public void setMinBathrooms(List<String> minBathrooms) {
        this.minBathrooms = minBathrooms;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }


}
