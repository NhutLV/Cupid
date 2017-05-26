package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;
import com.signatic.model.TagClass;

import java.util.ArrayList;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class APIResponseTags {

    //region Properties
    @SerializedName("error")
    private boolean mError;

    @SerializedName("tags")
    private ArrayList<TagClass> mData;

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

    public ArrayList<TagClass> getData() {
        return mData;
    }

    public void setData(ArrayList<TagClass> mData) {
        this.mData = mData;
    }

    //endregion
}
