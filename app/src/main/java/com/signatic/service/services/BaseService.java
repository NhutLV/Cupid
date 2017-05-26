package com.signatic.service.services;

/**
 * Created by NhutDu on 06/10/2016.
 */
public abstract class BaseService<T> {

    //region Properties

    T mService;

    //endregion

    //region Getter and Setter

    public T getService() {
        return mService;
    }

    //endregion
}
