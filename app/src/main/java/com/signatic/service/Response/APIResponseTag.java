package com.signatic.service.Response;

import com.google.gson.annotations.SerializedName;
import com.signatic.model.TagClass;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class APIResponseTag {

    //region Properties
    @SerializedName("error")
    private boolean mError;

    @SerializedName("tag")
    private TagClass mData;

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

    public TagClass getData() {
        return mData;
    }

    public void setData(TagClass data) {
        mData = data;
    }

    //endregion
}
