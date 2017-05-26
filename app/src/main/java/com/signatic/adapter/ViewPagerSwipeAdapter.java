package com.signatic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import com.signatic.fragment.ItemFragment;
import com.signatic.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NhutDu on 20/09/2016.
 */
public class ViewPagerSwipeAdapter extends FragmentPagerAdapter {
    ArrayList<User> mUsers;

    @Override
    public Fragment getItem(int position) {
        return ItemFragment.newInstance(mUsers.get(position));
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    public ViewPagerSwipeAdapter(FragmentManager fm, ArrayList<User> users) {
        super(fm);
        this.mUsers = users;
    }
}
