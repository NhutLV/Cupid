package com.signatic.model;

import java.util.Date;

/**
 * Created by DefaultAccount on 9/17/2016.
 */
public class Message {
    private String mMessage;
    private boolean mIsme;
    private Date mDate;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public boolean isme() {
        return mIsme;
    }

    public void setIsme(boolean isme) {
        mIsme = isme;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Message(String message, boolean isme, Date date) {
        mMessage = message;
        mIsme = isme;
        mDate = date;
    }
}
