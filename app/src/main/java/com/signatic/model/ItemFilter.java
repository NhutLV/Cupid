package com.signatic.model;

/**
 * Created by NhutDu on 16/09/2016.
 */
public class ItemFilter {

    //region Properties

    private String title;
    private boolean check;

    //endregion

    //region Getter and Setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    //endregion

    //region Constructor

    public ItemFilter(String title, boolean check) {
        this.title = title;
        this.check = check;
    }

    //endregion
}
