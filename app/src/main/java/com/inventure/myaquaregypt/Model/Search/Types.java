package com.inventure.myaquaregypt.Model.Search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aswany on 4/4/19.
 */

public class Types {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("type_id")
    @Expose
    private long typeId;

    /**
     * No args constructor for use in serialization
     */
    public Types() {
    }

    /**
     * @param type
     * @param typeId
     */
    public Types(String type, long typeId) {
        super();
        this.type = type;
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
