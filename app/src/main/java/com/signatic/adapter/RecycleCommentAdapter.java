package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.signatic.cupid.R;
import com.signatic.model.Comment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NhutDu on 22/09/2016.
 */
public class RecycleCommentAdapter extends RecyclerView.Adapter<RecycleCommentAdapter.CommentViewHolder>{

    //region Properties

    Context mContext;
    ArrayList<Comment> mComments;

    //endreregion

    public RecycleCommentAdapter(Context context,ArrayList<Comment> comments) {
        this.mContext = context;
        this.mComments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.mContent.setText(comment.getContent());
        holder.mNameUser.setText(comment.getUserComment().getUsername());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public class CommentViewHolder  extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mNameUser;
        TextView mContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_user);
            mNameUser = (TextView) itemView.findViewById(R.id.name_user);
            mContent = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
