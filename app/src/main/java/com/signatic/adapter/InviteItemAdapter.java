package com.signatic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.Friend;
import com.signatic.model.User;
import com.signatic.model.UserInterested;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DefaultAccount on 10/28/2016.
 */
public class InviteItemAdapter extends RecyclerView.Adapter<InviteItemAdapter.ViewHolder> {
    private ArrayList<UserInterested> mFriends;
    boolean mEdit;
    public InviteItemAdapter(ArrayList<UserInterested> friends,boolean edit) {
        mFriends = friends;
        mEdit=edit;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invite_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            User user=mFriends.get(position);
            holder.imageUser.setImageResource(R.drawable.image);
            holder.UserOrPhone.setText(user.getFirstName());
            if(mEdit){
                holder.minus.setVisibility(View.VISIBLE);
            }
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFriends.remove(position);
                    notifyDataSetChanged();
                }
            });

    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
         CircleImageView imageUser;
         TextView UserOrPhone;
         ImageView minus;
        public ViewHolder(View itemView) {
            super(itemView);
            imageUser= (CircleImageView) itemView.findViewById(R.id.imageUser);
            UserOrPhone= (TextView) itemView.findViewById(R.id.name);
            minus= (ImageView) itemView.findViewById(R.id.minus);
        }
    }
}
