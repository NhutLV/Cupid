package com.signatic.model;

/**
 * Created by DefaultAccount on 9/5/2016.
 */
public class Category {

    //region Properties
    private int mImage;

    private String mUsername;

    private int mGender;

    private String mTitle;

    private String mContent;

    private int nNumberFavorite;

    private String mAdress;
    //endregion Properties

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getnNumberFavorite() {
        return nNumberFavorite;
    }

    public void setnNumberFavorite(int nNumberFavorite) {
        this.nNumberFavorite = nNumberFavorite;
    }

    public String getAdress() {
        return mAdress;
    }

    public void setAdress(String adress) {
        mAdress = adress;
    }

    public Category(int image, String username, int gender, String title, String content, int nNumberFavorite, String adress) {
        mImage = image;
        mUsername = username;
        mGender = gender;
        mTitle = title;
        mContent = content;
        this.nNumberFavorite = nNumberFavorite;
        mAdress = adress;
    }
    //region get and set method

    public Category(){}
    //endregion constructor method
}
