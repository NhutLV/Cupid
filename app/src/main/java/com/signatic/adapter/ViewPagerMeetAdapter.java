package com.signatic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.signatic.fragment.MeetGlobalFragment;
import com.signatic.fragment.MeetNearbyFragment;

/**
 * Created by NhutDu on 18/10/2016.
 */
public class ViewPagerMeetAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPagerMeetAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MeetNearbyFragment tab1 = new MeetNearbyFragment();
                return tab1;
            case 1:
                MeetGlobalFragment tab2 = new MeetGlobalFragment();
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
