package com.signatic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.signatic.cupid.R;
import com.signatic.model.Chat;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by root on 02/11/2016.
 */

public class MessageMessengerAdapter extends RecyclerView.Adapter<MessageMessengerAdapter.ViewHolder>{
    private List<Chat> mDataSet;
    private String mId;
    private Context mContext;
    ImageLoader imageLoader;
    DisplayImageOptions options;
    private static final int CHAT_RIGHT = 1;
    private static final int CHAT_LEFT = 2;

    /**
     * Inner Class for a recycler view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        ImageView sendImage;
        CircleImageView mImageFace;
        CircleImageView mImageStatus;
        public ViewHolder(View v) {
            super(v);
            message = (TextView) itemView.findViewById(R.id.message_text);
            mImageFace= (CircleImageView) itemView.findViewById(R.id.image_face);
            mImageStatus= (CircleImageView) itemView.findViewById(R.id.image_status);
            sendImage=(ImageView) itemView.findViewById(R.id.sendimage);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param dataSet Message list
     * @param id      Device id
     */
    public MessageMessengerAdapter(List<Chat> dataSet, String id, Context mContext) {
        mId=id;
        mDataSet = dataSet;
        this.mContext=mContext;
        imageLoader=ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
    }

    @Override
    public MessageMessengerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == CHAT_RIGHT) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.add_chat_messager_right, parent, false);


        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.add_chat_messager_left, parent, false);

        }

        return new MessageMessengerAdapter.ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataSet.get(position).getId().equals(mId))
            return CHAT_RIGHT;

        return CHAT_LEFT;
    }

    @Override
    public void onBindViewHolder(final MessageMessengerAdapter.ViewHolder holder, int position) {
        Chat chat = mDataSet.get(position);
        int width= mContext.getResources().getDisplayMetrics().widthPixels;
        holder.message.setMaxWidth((int)(50*width)/64);
        holder.sendImage.setMaxWidth((int)(50*width)/64);
        holder.sendImage.setVisibility(View.GONE);
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        if (chat.getUrlImage() != null )
        {
            imageLoader.displayImage(chat.getUrlImage(),holder.sendImage,options);
            /*Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.sendImage.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                 *//* holder.sendImage.setImageDrawable(errorDrawable);*//*
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                   *//*holder.sendImage.setImageDrawable(placeHolderDrawable);*//*
                }
            };

            //this will help us to avoid the Target being gc'd
            holder.sendImage.setTag(target);

            Picasso.with(mContext)
                    .load(chat.getUrlImage())
                    .placeholder(RES_PLACEHOLDER)
                    .into(target);*/

            holder.message.setVisibility(View.GONE);
            holder.sendImage.setVisibility( View.VISIBLE );
            // Picasso.with( mContext ).load(chat.getUrlImage()).into(holder.sendImage);
        }if(chat.getMessage()!=null){
            holder.message.setText(chat.getMessage());
        }
       /* if(mDataSet.get(position).getId().equals(mId)) {
            if(position<1||(chat.getDateChat().getTime()-mDataSet.get((position-1)).getDateChat().getTime())/1000>600){
                String showDate=chat.getDateChat().toString().substring(4,10)+","+chat.getDateChat().toString().substring(11,16);
                if(Integer.parseInt(chat.getDateChat().toString().substring(11,13))>12)showDate=showDate+" PM";
                else showDate=showDate+" AM";
                holder.date.setText(showDate);
            }else{
                holder.date.setVisibility(View.GONE);
            }
        }else{
            holder.name.setText(chat.getUserChat());
        }*/
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
