package com.inventure.myaquaregypt.Model.OurServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ourServicesObj {
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("service_image")
    @Expose
    private String serviceImage;

    /**
     * No args constructor for use in serialization
     *
     */
    public ourServicesObj() {
    }

    /**
     *
     * @param serviceId
     * @param serviceImage
     * @param serviceName
     */
    public ourServicesObj(String serviceName, String serviceId, String serviceImage) {
        super();
        this.serviceName = serviceName;
        this.serviceId = serviceId;
        this.serviceImage = serviceImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

}
