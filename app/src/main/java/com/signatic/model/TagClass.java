package com.signatic.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nhutdu on 10/28/16.
 */
public class TagClass {

    //region properties
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    //endregion

    //region Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion

    //region Constructors

    public TagClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //endregion
}
