package com.inventure.myaquaregypt.Model.UnitsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aswany on 3/31/19.
 */

public class unitsModelRes {
    @SerializedName("project")
    @Expose
    private ArrayList<unitsModelObj> project = null;

    /**
     * No args constructor for use in serialization
     */
    public unitsModelRes() {
    }

    /**
     * @param project
     */
    public unitsModelRes(ArrayList<unitsModelObj> project) {
        super();
        this.project = project;
    }

    public ArrayList<unitsModelObj> getProject() {
        return project;
    }

    public void setProject(ArrayList<unitsModelObj> project) {
        this.project = project;
    }
}
