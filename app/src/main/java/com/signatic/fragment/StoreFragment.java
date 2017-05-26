package com.signatic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;

/**
 * Created by root on 08/11/2016.
 */

public class StoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView textView= (TextView) toolbar.findViewById(R.id.chat);
        ImageView mImgFilter = (ImageView) toolbar.findViewById(R.id.filter_interested);
        textView.setText("Store");
        mImgFilter.setImageDrawable(null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
