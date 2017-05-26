package com.signatic.utils;

import com.signatic.model.User;

/**
 * Created by NhutDu on 22/09/2016.
 */
public class UserLogin {

    private static User mUserLogin = null;

    public static User getUserLogin() {
        return mUserLogin;
    }

    public static void setUserLogin(User UserLogin) {
        mUserLogin = UserLogin;
    }
}
