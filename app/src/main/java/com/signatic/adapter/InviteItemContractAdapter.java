package com.signatic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.Friend;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 14/11/2016.
 */

public class InviteItemContractAdapter extends RecyclerView.Adapter<InviteItemContractAdapter.ViewHolder> {
    private ArrayList<Friend> mFriends;

    public InviteItemContractAdapter(ArrayList<Friend> friends) {
        mFriends = friends;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Friend friend = mFriends.get(position);
        holder.imageUser.setImageResource(R.drawable.image);
        holder.UserOrPhone.setText(friend.getUserName());

    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imageUser;
        TextView UserOrPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            imageUser = (CircleImageView) itemView.findViewById(R.id.imageUser);
            UserOrPhone = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
