package com.inventure.myaquaregypt.Model.OurServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ourServicesRes {

    @SerializedName("our_services")
    @Expose
    private ArrayList<ourServicesObj> ourServices = null;

    /**
     * No args constructor for use in serialization
     */
    public ourServicesRes() {
    }

    /**
     * @param ourServices
     */
    public ourServicesRes(ArrayList<ourServicesObj> ourServices) {
        super();
        this.ourServices = ourServices;
    }

    public ArrayList<ourServicesObj> getOurServices() {
        return ourServices;
    }

    public void setOurServices(ArrayList<ourServicesObj> ourServices) {
        this.ourServices = ourServices;
    }
}
