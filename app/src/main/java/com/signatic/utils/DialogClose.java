package com.signatic.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by NhutDu on 11/10/2016.
 */
public class DialogClose extends Dialog implements View.OnClickListener {

    //region Properties

    private Activity mActivity;

    //endregion

    //region Constructor


    public DialogClose(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

    }
}
