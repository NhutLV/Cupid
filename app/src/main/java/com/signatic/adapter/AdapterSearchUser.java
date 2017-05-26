package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 22/11/2016.
 */

public class AdapterSearchUser extends RecyclerView.Adapter<AdapterSearchUser.viewHolder> {

    private ArrayList<User> mUserArrayList;


    public AdapterSearchUser(ArrayList<User> userArrayList){
        mUserArrayList=userArrayList;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
         User u=mUserArrayList.get(position);
        holder.userName.setText(u.getDisplayName());
        holder.address.setText(u.getCountryCode());
        holder.numberInterest.setText(u.getInterestedIn()+"");
    }

    @Override
    public int getItemCount() {
        return mUserArrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView userName;
        TextView address;
        TextView numberInterest;
        public viewHolder(View itemView) {
            super(itemView);
            circleImageView= (CircleImageView) itemView.findViewById(R.id.userFace);
            userName= (TextView) itemView.findViewById(R.id.username);
            address=(TextView)itemView.findViewById(R.id.address);
            numberInterest=(TextView)itemView.findViewById(R.id.number_favorite);
        }
    }
}
