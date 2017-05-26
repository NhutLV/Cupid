package com.signatic.fragment;

import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.ViewPagerMeetAdapter;
import com.signatic.cupid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NhutDu on 18/10/2016.
 */
public class MeetMainFragment extends Fragment{

    @BindView(R.id.tab_meet_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager_meet)
    ViewPager mViewPager;

    ViewPagerMeetAdapter mAdapter;

    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meet,container,false);
        ButterKnife.bind(this,view);

        mAdapter = new ViewPagerMeetAdapter(getActivity().getSupportFragmentManager(),2);

        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("Nearby");
        mTabLayout.getTabAt(1).setText("Global");
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
