package com.signatic.model;

/**
 * Created by DefaultAccount on 10/4/2016.
 */
public class PackedCoin {
    private int mImage;
    private int mNumberCoin;
    private Double mMoney;
    private int mSave;

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public int getNumberCoin() {
        return mNumberCoin;
    }

    public void setNumberCoin(int numberCoin) {
        mNumberCoin = numberCoin;
    }

    public Double getMoney() {
        return mMoney;
    }

    public void setMoney(Double money) {
        mMoney = money;
    }

    public int getSave() {
        return mSave;
    }

    public void setSave(int save) {
        mSave = save;
    }

    public PackedCoin(){

    }

    public PackedCoin(int image, int numberCoin, Double money, int save) {
        mImage = image;
        mNumberCoin = numberCoin;
        mMoney = money;
        mSave = save;
    }
}
