package com.signatic.adapter;

import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.TagClass;
import com.signatic.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 22/11/2016.
 */

public class AdapterSearchTag  extends RecyclerView.Adapter<AdapterSearchTag.viewHolder> {

    private ArrayList<TagClass> mTags;


    public AdapterSearchTag(ArrayList<TagClass> tags){
        mTags=tags;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_tag,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        TagClass u=mTags.get(position);
        holder.tagName.setText(u.getName());
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        ImageView hash;
        TextView tagName;
        TextView postNumber;
        public viewHolder(View itemView) {
            super(itemView);
            hash= (ImageView) itemView.findViewById(R.id.hash);
            tagName=(TextView)itemView.findViewById(R.id.nametag);
            postNumber=(TextView)itemView.findViewById(R.id.numberPost);
        }
    }
}
