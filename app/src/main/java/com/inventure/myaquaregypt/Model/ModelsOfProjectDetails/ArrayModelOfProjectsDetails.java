package com.inventure.myaquaregypt.Model.ModelsOfProjectDetails;

import java.util.ArrayList;

public class ArrayModelOfProjectsDetails {


    public ArrayList<ModelObjectsOfProjectDetails> getProject() {
        return project;
    }

    public void setProject(ArrayList<ModelObjectsOfProjectDetails> project) {
        this.project = project;
    }

    private ArrayList<ModelObjectsOfProjectDetails> project = new ArrayList();

}
