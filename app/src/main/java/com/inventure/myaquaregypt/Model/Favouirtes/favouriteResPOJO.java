package com.inventure.myaquaregypt.Model.Favouirtes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aswany on 3/2/19.
 */

public class favouriteResPOJO {

    @SerializedName("favorites")
    @Expose
    private ArrayList<favouriteObjPOJO> favorites = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public favouriteResPOJO() {
    }

    /**
     *
     * @param favorites
     */
    public favouriteResPOJO(ArrayList<favouriteObjPOJO> favorites) {
        super();
        this.favorites = favorites;
    }

    public ArrayList<favouriteObjPOJO> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<favouriteObjPOJO> favorites) {
        this.favorites = favorites;
    }

}
