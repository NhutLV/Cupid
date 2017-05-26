package com.signatic.utils;

/**
 * Created by NhutDu on 07/11/2016.
 */
public interface Callback<T> {
    void next(T result);
}
