package com.signatic.fragment;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.signatic.cupid.R;
import com.signatic.model.User;
import com.signatic.model.UserInterested;
import com.signatic.service.Configuration;
import com.signatic.service.services.InteredUserimpl;
import com.signatic.service.services.TagServiceImpl;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.tindercard.SwipeFlingAdapterView;
import com.signatic.utils.Callback;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by DefaultAccount on 9/5/2016.
 */
public class InterestedFragment extends Fragment implements View.OnClickListener{
    private static MyAdapter myAdapter;
    private InteredUserimpl mInteredUserimpl;
    private ArrayList<UserInterested> mUsers = new ArrayList<>();
    private SwipeFlingAdapterView mSwipeFlingAdapterView;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;
    private int page;
    private UserServiceImpl mUserService = new UserServiceImpl();
    private User mUserLogin = UserLogin.getUserLogin();
    private UserInterested mUserRemove;
    private ArrayList<UserInterested> mUsersRemove = new ArrayList<>();
    private ArrayList<UserInterested> mUsersTemp = new ArrayList<>();
    private int currentIndexItem =20;
    private Button mBtnLike;
    private Button mBtnNope;
    private Button mBtnLocation;
    private Button mBtnNext;
    private int k=0;
    private ToolsActivity toolsActivity = new ToolsActivity();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static void removeBackground() {
        myAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        page=1;
        View view=inflater.inflate(R.layout.fragment_interested,container,false);
        mSwipeFlingAdapterView= (SwipeFlingAdapterView) view.findViewById(R.id.frame_interested);
        mInteredUserimpl=new InteredUserimpl(inflater.getContext());

        mBtnLike = (Button) view.findViewById(R.id.btn_interested_like);
        mBtnNope = (Button) view.findViewById(R.id.btn_interested_nope);
        mBtnNext = (Button) view.findViewById(R.id.btn_interested_next);
        mBtnLocation = (Button) view.findViewById(R.id.btn_interested_loaction);

        mBtnNext.setVisibility(View.INVISIBLE);

        mBtnNext.setOnClickListener(this);
        mBtnLocation.setOnClickListener(this);
        mBtnLike.setOnClickListener(this);
        mBtnNope.setOnClickListener(this);

        toolsActivity.showLoading(getActivity());
        mInteredUserimpl.getInteredUsers(UserLogin.getUserLogin(),page, new Callback<ArrayList<UserInterested>>() {
            @Override
            public void next(ArrayList<UserInterested> result) {
                toolsActivity.dismissLoading(getActivity());

                mUsers.addAll(result);
                myAdapter=new MyAdapter(mUsers);
                mSwipeFlingAdapterView.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                mSwipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                    @Override
                    public void removeFirstObjectInAdapter() {
                    }

                    @Override
                    public void onLeftCardExit(Object dataObject) {
                        mUserRemove = mUsers.get(0);
                        mBtnNext.setVisibility(View.VISIBLE);
                        mUsersRemove.add(0,mUsers.get(0));
                        mUsers.remove(0);
                        currentIndexItem--;
                        getNextPage();
                        myAdapter.notifyDataSetChanged();
                        myAdapter.notifyDataSetInvalidated();
                    }

                    @Override
                    public void onRightCardExit(Object dataObject) {
                        mUserRemove = mUsers.get(0);
                        mBtnNext.setVisibility(View.VISIBLE);
                        doLikeUser(mUsers.get(0).getID());
                        mUsersRemove.add(0,mUsers.get(0));
                        mUsers.remove(0);
                        currentIndexItem--;
                        getNextPage();
                        myAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onAdapterAboutToEmpty(int itemsInAdapter) {

                    }

                    @Override
                    public void onScroll(float scrollProgressPercent) {
                        View view = mSwipeFlingAdapterView.getSelectedView();
                        view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                        view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
                    }

                });
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_interested_like:
                doLikeUser(mUsers.get(0).getID());
                currentIndexItem--;
                mUserRemove = mUsers.get(0);
                mUsersRemove.add(0,mUsers.get(0));
                mUsers.remove(0);
                getNextPage();
                mBtnNext.setVisibility(View.VISIBLE);
                resetLayout();
                break;
            case R.id.btn_interested_next:
                currentIndexItem++;
//                for(int i=mUsersRemove.size()-1,j=0;i>=0;i--,j++){
//                    mUsersTemp.set(j,mUsersRemove.get(i));
//                }
                Log.d("User remove ",mUsers.get(0).getID()+"");
                Log.d("K =",k+"");
                mUsers.add(0,mUsersRemove.get(k));
                k = k+1;
                if(k==mUsersRemove.size()){
                    mBtnNext.setVisibility(View.INVISIBLE);
                    mUsersRemove.removeAll(mUsersRemove);
                    k=0;
                }
                Log.d("SIZE K",mUsersRemove.size()+"");
                Log.d("K 1 = ",k+"");
                resetLayout();
                break;
            case R.id.btn_interested_nope:
                mUserRemove = mUsers.get(0);
                currentIndexItem--;
                mUsersRemove.add(0,mUsers.get(0));
                mUsers.remove(0);
                getNextPage();
                mBtnNext.setVisibility(View.VISIBLE);
                resetLayout();
                break;
        }
    }

    //region Private methods
    private void doLikeUser(int user_like_id){
        mUserService.updateLikeUser(user_like_id, mUserLogin.getID(), new Callback<Boolean>() {
            @Override
            public void next(Boolean result) {
                if(result){
                    Toast.makeText(getActivity().getApplicationContext(),"Like successfully ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Like failed ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetLayout(){
        myAdapter=new MyAdapter(mUsers);
        mSwipeFlingAdapterView.setAdapter(myAdapter);
        mSwipeFlingAdapterView.removeAllViewsInLayout();
        myAdapter.notifyDataSetChanged();
        myAdapter.notifyDataSetInvalidated();
    }

    //endregion

    public static class ViewHolder {

        public  FrameLayout background;
        public CircleImageView imageAvatar;
        public TextView displayName;
        public ImageView imageCover;

    }
    public  class MyAdapter extends BaseAdapter {
        private ArrayList<UserInterested> mUsers;
        public TagServiceImpl mTagService;
        public Button[] button= new Button[5];
        public int[] idButton={R.id.tag1,R.id.tag2,R.id.tag3,R.id.tag4,R.id.tag5};
        public MyAdapter(ArrayList<UserInterested> mUsers){
            this.mUsers=mUsers;
            mTagService=new TagServiceImpl(getContext());
        }
        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if (rowView == null) {
                rowView = inflater.inflate(R.layout.items_fragment_interested, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.displayName = (TextView) rowView.findViewById(R.id.interest_name);
                viewHolder.imageAvatar = (CircleImageView) rowView.findViewById(R.id.image_avatar_interested);
                viewHolder.imageCover = (ImageView) rowView.findViewById(R.id.image_cover_interested);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.displayName.setText(mUsers.get(position).getDisplayName() + mUsers.get(position).getID());
            Picasso.with(getContext()).load(Configuration.UPLOAD_IMAGE_URL+mUsers.get(position).getAvatarUrl())
                                      .placeholder(R.drawable.avatar_friends2x)
                                      .into(viewHolder.imageAvatar);
            Picasso.with(getContext()).load(Configuration.UPLOAD_IMAGE_URL+mUsers.get(position).getPhoto())
                    .placeholder(R.drawable.image)
                    .fit().centerInside()
                    .into(viewHolder.imageCover);
            return rowView;
        }
    }
    public void getNextPage(){
        if(currentIndexItem==0){
            page++;
            currentIndexItem=20;
            toolsActivity.showLoading(getActivity());
            mInteredUserimpl.getInteredUsers(UserLogin.getUserLogin(),page, new Callback<ArrayList<UserInterested>>() {
                @Override
                public void next(ArrayList<UserInterested> result) {
                    toolsActivity.dismissLoading(getActivity());
                    mUsers.addAll(result);
                    myAdapter = new MyAdapter(mUsers);
                    mSwipeFlingAdapterView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
        });
    }
   }

    public void getPreviousPage(){
        if(currentIndexItem==20 && page>1 ){
            page--;
            currentIndexItem=0;
            mInteredUserimpl.getInteredUsers(UserLogin.getUserLogin(),page, new Callback<ArrayList<UserInterested>>() {
                @Override
                public void next(ArrayList<UserInterested> result) {
                    mUsers.addAll(result);
                    myAdapter = new MyAdapter(mUsers);
                    mSwipeFlingAdapterView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private Tag addTagTo(String s, boolean isDelete) {
        Tag tag;
        tag = new Tag(s);
        tag.layoutColor = Color.parseColor("#17A1CA");
        tag.radius = 10f;
        tag.isDeletable = isDelete;
        return tag;
    }
}
