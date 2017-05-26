package com.signatic.fragment;
import android.support.v7.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.EndlessRecyclerViewScrollListener;
import com.signatic.adapter.RecyclerItemClickListener;
import com.signatic.adapter.SpacesItemDecoration;
import com.signatic.adapter.StaggedGridAdapter;
import com.signatic.cupid.ItemFragmentActivity;
import com.signatic.cupid.R;
import com.signatic.model.User;
import com.signatic.event.UserEvent;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.UserLogin;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by NhutDu on 15/09/2016.
 */
public abstract class BaseFragment extends Fragment{

    //region Properties
    protected RecyclerView mRecyclerView;
    protected StaggedGridAdapter mAdapter;
    protected ArrayList<User> mUsers ;
    StaggeredGridLayoutManager mLayoutManager;
    EndlessRecyclerViewScrollListener mScrollListener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            mUsers = new ArrayList<>();
            View view = inflater.inflate(R.layout.fragment_base,container,false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_meet);
            mAdapter  = new StaggedGridAdapter(getActivity(),mUsers);
            mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ItemFragmentActivity.class);
                    EventBus.getDefault().postSticky(new UserEvent(mUsers,position));
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                }

                @Override
                public void onLongItemClick(View view, int position) {

                }
            }));
            mRecyclerView.setAdapter(mAdapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(10);
            mRecyclerView.addItemDecoration(decoration);

            return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUsers = null;
    }


}

