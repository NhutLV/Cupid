package com.signatic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.AdapterSearchUser;
import com.signatic.adapter.InviteDividerItemDecoration;
import com.signatic.cupid.R;
import com.signatic.model.User;

import java.util.ArrayList;

/**
 * Created by root on 22/11/2016.
 */

public class SearchUserFragment extends Fragment {
    public ArrayList<User> mUserArrayList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.search_usertag_fragment,container,false);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycleSearchUser);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        createArray();
        AdapterSearchUser adapterSearchUser=new AdapterSearchUser(mUserArrayList);
        recyclerView.setAdapter(adapterSearchUser);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new InviteDividerItemDecoration(inflater.getContext()));
        return view;
    }
    public void createArray(){
        mUserArrayList=new ArrayList<>();
        for(int i=0;i<10;i++) {
            User u=new User();
            u.setDisplayName("Hung Pham");
            u.setInterestedIn(65);
            u.setCountryCode("London,UK");
            mUserArrayList.add(u);
        }
    }

}
