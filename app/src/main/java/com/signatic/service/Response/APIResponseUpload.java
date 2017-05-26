package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NhutDu on 22/11/2016.
 */

public class APIResponseUpload {

    //region Properties

    @SerializedName("success")
    private int mSuccess;

    @SerializedName("data")
    private String [] mData;

    //endregion

    //region Getter and Setter

    public int getSuccess() {
        return mSuccess;
    }

    public void setSuccess(int success) {
        mSuccess = success;
    }

    public String[] getData() {
        return mData;
    }

    public void setData(String[] data) {
        mData = data;
    }

    //endregion
}
