package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;
import com.signatic.model.User;
import com.signatic.model.UserInterested;

import org.json.JSONArray;

import java.util.ArrayList;


/**
 * Created by root on 11/11/2016.
 */

public class APIResponseInteredUser {
    @SerializedName("error")
    private boolean mError;

    @SerializedName("users")
    private ArrayList<UserInterested> mUsers;

    @SerializedName("message")
    private String mMessage;

    public boolean isError() {
        return mError;
    }

    public void setError(boolean mError) {
        this.mError = mError;
    }

    public ArrayList<UserInterested> getmUsers() {
        return mUsers;
    }

    public void setmUsers(ArrayList<UserInterested> mUsers) {
        this.mUsers = mUsers;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
