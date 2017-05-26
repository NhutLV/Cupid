package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.signatic.cupid.R;
import com.signatic.model.Category;

import java.util.ArrayList;

/**
 * Created by DefaultAccount on 9/5/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyListAdapter> {

    private ArrayList<Category> mCategoryList;
    private Context mContext;
    public ListAdapter(ArrayList<Category> categoryList, Context context){
        this.mCategoryList=categoryList;
        this.mContext=context;
    }
    @Override
    public MyListAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.abum_card,parent,false);
        return new MyListAdapter(view);
    }

    @Override
    public void onBindViewHolder(MyListAdapter holder, int position) {
            Category mCategory=mCategoryList.get(position);
            holder.imageCategory.setImageResource(mCategory.getImage());
            holder.imageGender.setImageResource(mCategory.getGender());
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class MyListAdapter extends RecyclerView.ViewHolder{
        private ImageView imageCategory,imageGender;
        public MyListAdapter(View itemView) {
            super(itemView);
            imageCategory= (ImageView) itemView.findViewById(R.id.image_category);
            imageGender=(ImageView) itemView.findViewById(R.id.image_gender);
        }

    }
}
