package com.inventure.myaquaregypt.InternalStorage;

import com.inventure.myaquaregypt.Model.HomeApi.ModelObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswany on 3/12/19.
 */

public class Session {

    private static Session instance;

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    private Session() {
    }

    //home array
    private ArrayList<ModelObjects> HomeArray;

    //images
    private List<String> urlimage = new ArrayList<>();

    //Structure Images
    private List<String> StructureImages = new ArrayList<>();
    //TypesID
    private String TypesOfUnitID;
    //ProjectID
    private int ProjectID;


    //Getter & Setters
    public List<String> getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(List<String> urlimage) {
        this.urlimage = urlimage;
    }

    public ArrayList<ModelObjects> getHomeArray() {
        return HomeArray;
    }

    public void setHomeArray(ArrayList<ModelObjects> homeArray) {
        this.HomeArray = homeArray;
    }

    public List<String> getStructureImages() {
        return StructureImages;
    }

    public void setStructureImages(List<String> structureImages) {
        StructureImages = structureImages;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }

    public String getTypesOfUnitID() {
        return TypesOfUnitID;
    }

    public void setTypesOfUnitID(String typesOfUnitID) {
        TypesOfUnitID = typesOfUnitID;
    }
}
