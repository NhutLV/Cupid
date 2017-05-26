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

import com.signatic.adapter.AdapterSearchTag;
import com.signatic.adapter.InviteDividerItemDecoration;
import com.signatic.cupid.R;
import com.signatic.model.TagClass;

import java.util.ArrayList;

/**
 * Created by root on 22/11/2016.
 */

public class SearchTagFragment extends Fragment {
    private ArrayList<TagClass> mTagClasses;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_usertag_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleSearchUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        createArray();
        AdapterSearchTag adapterSearchTag=new AdapterSearchTag(mTagClasses);
        recyclerView.setAdapter(adapterSearchTag);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new InviteDividerItemDecoration(inflater.getContext()));
        return view;
    }
    public void createArray(){
        mTagClasses=new ArrayList<>();
        for(int i=0;i<10;i++){
            TagClass tagClass=new TagClass(i,"eveBrows");
            mTagClasses.add(tagClass);
        }
    }
}
