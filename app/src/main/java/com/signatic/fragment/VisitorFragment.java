package com.signatic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.EndlessRecyclerViewScrollListener;
import com.signatic.cupid.MainActivity;
import com.signatic.cupid.R;
import com.signatic.model.User;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.Callback;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by NhutDu on 15/09/2016.
 */
public class VisitorFragment extends BaseFragment {

    UserServiceImpl mUserService = new UserServiceImpl();

    @Override
    public void onCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        final ToolsActivity toolsActivity = new ToolsActivity();
        toolsActivity.showLoading(getActivity());

        mUserService.getVisitorUser(UserLogin.getUserLogin().getID(), 1,UserLogin.getUserLogin().getApiKey(), new Callback<ArrayList<User>>() {
            @Override
            public void next(ArrayList<User> result) {
                mUsers.addAll(result);
                mAdapter.notifyDataSetChanged();
                toolsActivity.dismissLoading(getActivity());
            }
        });

        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mUserService.getVisitorUser(UserLogin.getUserLogin().getID(), page,UserLogin.getUserLogin().getApiKey(), new Callback<ArrayList<User>>() {
                    @Override
                    public void next(ArrayList<User> result) {
                        mUsers.addAll(result);
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        };

        mRecyclerView.addOnScrollListener(mScrollListener);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
