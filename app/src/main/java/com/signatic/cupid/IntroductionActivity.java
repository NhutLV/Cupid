package com.signatic.cupid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.signatic.utils.PrefManager;

/**
 * Created by NhutDu on 08/09/2016.
 */
public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    //region Properties

    int [] mLayouts;
    private Button mBtnSkip;
    private LinearLayout mDotsLayout;
    private ViewPager mViewPager;
    private MyViewPagerAdapter mViewPagerAdapter;
    private TextView [] mDots;
    private PrefManager mPrefManager;
    private static final String TAG = IntroductionActivity.class.getSimpleName();

    //endregion

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        getSupportActionBar().hide();

        mPrefManager = new PrefManager(this);
        if(!mPrefManager.isFirstTime()){
            loadMainScreen();
            finish();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        mLayouts = new int[]{R.layout.introduction_slide1
                            ,R.layout.introduction_slide2
                            ,R.layout.introduction_slide3
                            ,R.layout.introduction_slide4};

        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

        addBottomDots(0);

        changeStatusBarColor();

        mViewPagerAdapter = new MyViewPagerAdapter();
        mViewPager.addOnPageChangeListener(onPageChangeListener);
        mViewPager.setAdapter(mViewPagerAdapter);
        mBtnSkip.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_skip:
                loadMainScreen();
                break;
            case R.id.btn_next:
                int currentPage = getItem(1);
                if(currentPage<mLayouts.length){
                    mViewPager.setCurrentItem(currentPage);
                }else{
                    loadMainScreen();
                }
                break;
        }
    }

    //region change color statusBar

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //endregion

    //region private methods

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_color_light);
        int[] colorsInactive = getResources().getIntArray(R.array.array_color_dark);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInactive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0)
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if(position==mLayouts.length-1){
                mBtnSkip.setText(getString(R.string.start));
            }else{
                mBtnSkip.setText(getString(R.string.skip));
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //endregion

    public void loadMainScreen(){
        mPrefManager.setIsFristTime(true);
        Intent intent = new Intent(IntroductionActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public int getItem(int i){
        int currentPage = mViewPager.getCurrentItem()+1;
        return currentPage;
    }

    //region custom PagerAdapter ViewPager

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(mLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    //endregion
}
