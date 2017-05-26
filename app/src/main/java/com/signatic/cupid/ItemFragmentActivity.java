package com.signatic.cupid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.signatic.adapter.ViewPagerSwipeAdapter;
import com.signatic.model.User;
import com.signatic.event.UserEvent;
import com.signatic.swipeblack.Position;
import com.signatic.swipeblack.SwipeBack;
import com.signatic.utils.ToolsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NhutDu on 19/09/2016.
 */
public class ItemFragmentActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_fragment)
    ViewPager mViewPager;

    ArrayList<User> mUsers=null;

    int mPositionSlide =0;
    private int mPagerPosition;
    private int mPagerOffsetPixels;
    private EventBus mEventBus;
    ViewPagerSwipeAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventBus = EventBus.getDefault();
        mEventBus.register(this);
        ToolsActivity.setColorStatus(this,R.color.colorBlack);
        SwipeBack.attach(this, Position.LEFT)
                .setContentView(R.layout.activity_item_fragment)
                .setSwipeBackView(com.example.library.R.layout.swipeback_default)
                .setDividerAsSolidColor(Color.WHITE)
                .setDividerSize(2)
                .setOnInterceptMoveEventListener(new SwipeBack.OnInterceptMoveEventListener() {
                    @Override
                    public boolean isViewDraggable(View v, int delta, int x, int y) {
                        if (v == mViewPager) {
                            return !(mPagerPosition == 0 && mPagerOffsetPixels == 0)
                                    || delta < 0;
                        }

                        return false;
                    }
                });
        ButterKnife.bind(this);
        mAdapter = new ViewPagerSwipeAdapter(getSupportFragmentManager(), mUsers);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPositionSlide);
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                page.setRotationY(position * -30);
            }
        });

//        mViewPager.setOffscreenPageLimit(mUsers.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPagerPosition = position;
                mPagerOffsetPixels = positionOffsetPixels;
//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUsers = null;
        mEventBus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(UserEvent userEvent){
        mUsers = userEvent.getUsers();
        mPositionSlide = userEvent.getPosition();
        mEventBus.unregister(this);
    }


    public class LagAdpater extends PagerAdapter {
        LayoutInflater mInflater;
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = mInflater.inflate(R.layout.fragment_item_fragment,container,false);

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mUsers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==(View)object);
        }
    }
}
