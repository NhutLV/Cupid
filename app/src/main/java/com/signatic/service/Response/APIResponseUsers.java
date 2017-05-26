package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;
import com.signatic.model.TagClass;
import com.signatic.model.User;

import java.util.ArrayList;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class APIResponseUsers {

    //region Properties
    @SerializedName("error")
    private boolean mError;

    @SerializedName("users")
    private ArrayList<User> mUsers;

    @SerializedName("message")
    private String mMessage;

    //endregion

    //region Getter and Setter

    public boolean isError() {
        return mError;
    }

    public void setError(boolean error) {
        mError = error;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }

    //endregion
}
