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
import com.signatic.service.Configuration;
import com.signatic.utils.UserLogin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by NhutDu on 15/09/2016.
 */
public class StaggedGridAdapter extends RecyclerView.Adapter<StaggedGridAdapter.StaggedGridView>{

    //region Properties

    private ArrayList<User> mUsers;
    private Context mContext;

    //endregion

    //region Constructor

    public StaggedGridAdapter(Context context,ArrayList<User> users) {
        mUsers = users;
        mContext = context;
    }

    //endregion

    @Override
    public StaggedGridView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggedgrid,parent,false);

        return new StaggedGridView(view);
    }

    @Override
    public void onBindViewHolder(StaggedGridView holder, int position) {
        User user = mUsers.get(position);
        holder.age.setText(user.getAge()+"");
        holder.name.setText(user.getFirstName()+ " " +user.getLastName());
        Picasso.with(mContext).load(Configuration.UPLOAD_IMAGE_URL+ user
                            .getAvatarUrl())
                            .placeholder(R.drawable.avatar_friends2x)
                            .into(holder.imageAvatar);
        Picasso.with(mContext).load(Configuration.UPLOAD_IMAGE_URL+ user
                            .getPhoto())
                            .placeholder(R.drawable.image_category)
                            .into(holder.imageCover);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class StaggedGridView extends RecyclerView.ViewHolder{

        ImageView imageAvatar;
        ImageView imageCover;
        TextView age;
        TextView name;

        public StaggedGridView(View itemView) {
            super(itemView);
            imageAvatar = (ImageView) itemView.findViewById(R.id.image_avatar_fragment_item);
            imageCover = (ImageView) itemView.findViewById(R.id.image_cover_fragment_item);
            age = (TextView) itemView.findViewById(R.id.age_profile_fragment);
            name = (TextView) itemView.findViewById(R.id.name_profile_fragment);
        }
    }

}
