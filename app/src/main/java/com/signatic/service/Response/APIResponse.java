package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class APIResponse<T> {

    //region Properties
    @SerializedName("error")
    private boolean mError;

    @SerializedName("data")
    private T mData;

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

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    //endregion
}
