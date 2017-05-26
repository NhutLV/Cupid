package com.signatic.event;

import com.signatic.model.User;

import java.util.ArrayList;

/**
 * Created by NhutDu on 10/10/2016.
 */
public class UserEvent {

    //region Properties

    private ArrayList<User> mUsers;

    private int mPosition;

    //endregion

    //region Getter and Setter

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }


    //endregion

    //region Constructor

    public UserEvent(ArrayList<User> users, int position) {
        mUsers = users;
        mPosition = position;
    }

    //endregion
}
