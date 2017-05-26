package com.signatic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.signatic.adapter.ViewPagerMeetAdapter;
import com.signatic.adapter.ViewPagerSearchAdapter;
import com.signatic.cupid.R;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 22/11/2016.
 */

public class SearchMainFragment extends Fragment {

    @BindView(R.id.tab_meet_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager_meet)
    ViewPager mViewPager;


    EditText mEdit;

    ViewPagerSearchAdapter mAdapter;

    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout,container,false);
        ButterKnife.bind(this,view);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        Toolbar newToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar2);
        newToolbar.setVisibility(View.VISIBLE);
        mEdit=(EditText)newToolbar.findViewById(R.id.startSearch);
        mAdapter = new ViewPagerSearchAdapter(getActivity().getSupportFragmentManager(),2);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("Users");
        mTabLayout.getTabAt(1).setText("Tags");
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG meet","Resume");
    }
}
