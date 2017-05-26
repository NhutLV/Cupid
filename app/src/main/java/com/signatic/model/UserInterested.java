package com.signatic.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 15/11/2016.
 */

public class UserInterested extends User{

    @SerializedName("distance")
    private double mDistance;

    public UserInterested() {
    }

    public UserInterested(String firstName, String lastName, String email, String password, int age, String avatarUrl, double latitude, double longtitude, String countryCode, int gender, double distance) {
        super(firstName, lastName, email, password, age, avatarUrl, latitude, longtitude, countryCode, gender);
        this.mDistance=distance;
    }

    public double getDistance() {
        return mDistance;
    }

    public void setDistance(double mDistance) {
        this.mDistance = mDistance;
    }
}
