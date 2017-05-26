package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.ItemFilter;

import java.util.ArrayList;

/**
 * Created by NhutDu on 16/09/2016.
 */
public class RecycleFilterAdapter extends RecyclerView.Adapter<RecycleFilterAdapter.MyViewHolder>{

    Context mContext;
    ArrayList<ItemFilter> mItems;

    public RecycleFilterAdapter(Context context, ArrayList<ItemFilter> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fileter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemFilter item = mItems.get(position);
        holder.title.setText(item.getTitle());
        holder.check.setChecked(item.isCheck());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private CheckBox check;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            check = (CheckBox) itemView.findViewById(R.id.check);
        }
    }

}
