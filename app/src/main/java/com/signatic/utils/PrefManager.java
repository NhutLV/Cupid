package com.signatic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NhutDu on 12/09/2016.
 */
public class PrefManager {

    private Context mContext;
    private static final String IS_FRIST_TIME="IS_FIRST_TIME";
    private static final String PREF_NAME="CUPID";
    private int PRIVATE_MODE =0;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPref;

    public PrefManager(Context context){

        this.mContext = context;
        mPref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        mEditor = mPref.edit();

    }

    public void setIsFristTime(boolean isFristTime){
        mEditor.putBoolean(IS_FRIST_TIME,isFristTime);
        mEditor.commit();
    }

    public boolean isFirstTime(){
        return mPref.getBoolean(IS_FRIST_TIME,true);
    }

}
