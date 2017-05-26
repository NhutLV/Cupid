package com.signatic.fragment;

import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.RecycleFilterAdapter;
import com.signatic.cupid.R;
import com.signatic.library.horizontalpicker.HorizontalPicker;
import com.signatic.model.ItemFilter;
import com.signatic.utils.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by NhutDu on 16/09/2016.
 */
public class FilterFragment extends Fragment {
    //region Constructor

    public FilterFragment() {
    }

    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filter,container,false);
        final HorizontalPicker horizontalPicker = (HorizontalPicker) view.findViewById(R.id.picker);
        horizontalPicker.setOnItemClickedListener(new HorizontalPicker.OnItemClicked() {
            @Override
            public void onItemClicked(int index) {

            }
        });
        horizontalPicker.setOnItemSelectedListener(new HorizontalPicker.OnItemSelected() {
            @Override
            public void onItemSelected(int index) {

            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_filter);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.rv_filter2);
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.rv_filter3);
        RecycleFilterAdapter adapter = new RecycleFilterAdapter(getContext(),data());
        RecycleFilterAdapter adapter2 = new RecycleFilterAdapter(getContext(),data2());
        RecycleFilterAdapter adapter3 = new RecycleFilterAdapter(getContext(),data3());
        setupRecycleView(recyclerView,adapter);
        setupRecycleView(recyclerView2,adapter2);
        setupRecycleView(recyclerView3,adapter3);
        return view;
    }

    public ArrayList<ItemFilter> data(){
        ArrayList<ItemFilter> itemFilters = new ArrayList<>();
        itemFilters.add(new ItemFilter("Everyone",false));
        itemFilters.add(new ItemFilter("Everyone who likes girls",false));
        itemFilters.add(new ItemFilter("Everyone who likes guys",false));
        itemFilters.add(new ItemFilter("All girls",false));
        itemFilters.add(new ItemFilter("Girls who like girls",false));
        itemFilters.add(new ItemFilter("Girls who like guys",false));
        itemFilters.add(new ItemFilter("All guys",false));
        itemFilters.add(new ItemFilter("Guys who like girls",false));
        itemFilters.add(new ItemFilter("Guys who like guys",false));
        itemFilters.add(new ItemFilter("Boys",false));

        return itemFilters;
    }
    public ArrayList<ItemFilter> data3(){
        ArrayList<ItemFilter> itemFilters = new ArrayList<>();
        itemFilters.add(new ItemFilter("1",false));
        return itemFilters;
    }
    public ArrayList<ItemFilter> data2(){
        ArrayList<ItemFilter> itemFilters = new ArrayList<>();
        itemFilters.add(new ItemFilter("1",false));
        return itemFilters;
    }

    private String getData(int i){
        String [] arr =getResources().getStringArray(R.array.values_age);
        return arr[i];
    }

    public void setupRecycleView(RecyclerView recyclerView,RecycleFilterAdapter adapter){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
