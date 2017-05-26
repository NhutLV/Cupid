package com.signatic.fragment;

import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.signatic.cupid.R;
import com.signatic.model.User;
import com.signatic.tindercard.SwipeFlingAdapterView;
import com.signatic.utils.UserLogin;

import java.util.ArrayList;

import static com.signatic.cupid.R.color.color_app;

/**
 * Created by DefaultAccount on 9/5/2016.
 */
public class DetailFragment extends Fragment{
    private static ViewHolder viewHolder;
    private static MyAdapter myAdapter;
    private ArrayList<User> mUsers;
    private SwipeFlingAdapterView mSwipeFlingAdapterView;
    public LayoutInflater inflater;

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
        View view=inflater.inflate(R.layout.detail_fragment_layout,container,false);
        mSwipeFlingAdapterView= (SwipeFlingAdapterView) view.findViewById(R.id.frame);

        mUsers=new ArrayList<>();
        mUsers.add(new User("Loi Nguyen","123456","Le Loi Street","012553789"));
        mUsers.add(new User("Loi Nguyen","123456","Le Loi Street","012553789"));
        mUsers.add(new User("Loi Nguyen","123456","Le Loi Street","012553789"));
        mUsers.add(new User("Loi Nguyen","123456","Le Loi Street","012553789"));
        mUsers.add(new User("Loi Nguyen","123456","Le Loi Street","012553789"));
        myAdapter=new MyAdapter(mUsers);
        mSwipeFlingAdapterView.setAdapter(myAdapter);
        mSwipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                mUsers.remove(0);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                mUsers.remove(0);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }

        });
        /*mSwipeFlingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = mSwipeFlingAdapterView.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAdapter.notifyDataSetChanged();
            }
        });*/
        return view;

    }

    public static class ViewHolder {

        public  FrameLayout background;
        public ImageView image;
        public LinearLayout llinfo,llfloatbutton;
        public TagView mTagViews;
    }
    public  class MyAdapter extends BaseAdapter {
        private ArrayList<User> mUsers;

        public MyAdapter(ArrayList<User> mUsers){
            this.mUsers=mUsers;

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
            View view=convertView;
            if(view == null){

                view = inflater.inflate(R.layout.item_detail_fragment_layout, parent, false);
                viewHolder=new ViewHolder();
                viewHolder.image= (ImageView) view.findViewById(R.id.image_face);
                viewHolder.llinfo= (LinearLayout) view.findViewById(R.id.info);
                viewHolder.llfloatbutton= (LinearLayout) view.findViewById(R.id.llfloatbutton);
                viewHolder.mTagViews = (TagView) view.findViewById(R.id.tag_view_layout_main);
//                for(int i =0;i< UserLogin.getmUserLogin().getTags().size();i++){
//                    tag = new Tag(UserLogin.getmUserLogin().getTags().get(i).getName());
//                    tag.radius = 10f;
//                    viewHolder.mTagViews.addTag(tag);
//                }
                Tag tag = new Tag("aaa");
                tag.tagTextColor = R.color.color_app;
                viewHolder.mTagViews.addTag(tag);

                view.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            return view;
        }

    }

}
