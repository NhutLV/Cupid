package com.signatic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.signatic.fragment.MeetGlobalFragment;
import com.signatic.fragment.MeetNearbyFragment;
import com.signatic.fragment.SearchTagFragment;
import com.signatic.fragment.SearchUserFragment;

/**
 * Created by root on 22/11/2016.
 */

public class ViewPagerSearchAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerSearchAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SearchUserFragment tab1 = new SearchUserFragment();
                return tab1;
            case 1:
                SearchTagFragment tab2 = new SearchTagFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
