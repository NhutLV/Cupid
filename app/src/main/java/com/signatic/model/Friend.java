package com.signatic.model;

/**
 * Created by DefaultAccount on 10/28/2016.
 */
public class Friend {
    private String urlImage;
    private String UserName;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Friend(String urlImage, String userName) {
        this.urlImage = urlImage;
        UserName = userName;
    }
}
