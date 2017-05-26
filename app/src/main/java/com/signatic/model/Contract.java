package com.signatic.model;

/**
 * Created by DefaultAccount on 10/28/2016.
 */
public class Contract {
    String mPhoneNumber;
    String mUserName;

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public Contract(String phoneNumber, String userName) {
        mPhoneNumber = phoneNumber;
        mUserName = userName;
    }
}
