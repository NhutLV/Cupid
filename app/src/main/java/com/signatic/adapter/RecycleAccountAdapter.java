package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.ItemFilter;

import java.util.ArrayList;

/**
 * Created by NhutDu on 16/09/2016.
 */
public class RecycleAccountAdapter extends RecyclerView.Adapter<RecycleAccountAdapter.MyViewHolder>{

    Context mContext;
    ArrayList<String> mItems;

    public RecycleAccountAdapter(Context context, ArrayList<String> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String item = mItems.get(position);
        holder.title.setText(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
        }
    }

}
