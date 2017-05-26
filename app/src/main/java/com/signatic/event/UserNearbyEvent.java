package com.signatic.event;

import com.signatic.model.User;

import java.util.ArrayList;

/**
 * Created by NhutDu on 15/11/2016.
 */

public class UserNearbyEvent {

    private ArrayList<User> mUsers;

    public ArrayList<User> getUsers() {
        return mUsers;
    }

    public void setUsers(ArrayList<User> users) {
        mUsers = users;
    }

    public UserNearbyEvent(ArrayList<User> users) {
        mUsers = users;
    }
}
