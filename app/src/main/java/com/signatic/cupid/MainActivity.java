
package com.signatic.cupid;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.signatic.fragment.ChatFireBaseFragment;
import com.signatic.fragment.FilterFragment;
import com.signatic.fragment.InterestedFragment;
import com.signatic.fragment.InviteFragment;
import com.signatic.fragment.MeetMainFragment;
import com.signatic.fragment.SearchMainFragment;
import com.signatic.fragment.VisitorFragment;
import com.signatic.fragment.WhoLikeMeFragment;
import com.signatic.service.Configuration;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FragmentManager fragmentManager;
    ImageView mImageView;
    TextView mSettingProfile;
    TextView mUsernameLogin;
    ImageView mImgAvatar;
    public ImageView mImgFilter;
    public TextView mTitleChat;
    public Toolbar toolbar,toolbarSearch;
    SessionManager mSessionManager;

    @TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarSearch =(Toolbar)findViewById(R.id.toolbar2);
        toolbarSearch.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbarSearch);
        mImageView = (ImageView) toolbar.findViewById(R.id.toolbar_title);
        mImgFilter = (ImageView) toolbar.findViewById(R.id.filter_interested);
        mTitleChat = (TextView)  toolbar.findViewById(R.id.chat);
        setTitle("");

        toolbar.setNavigationIcon(R.drawable.menu);
        toolbarSearch.setNavigationIcon(R.drawable.menu);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        ActionBarDrawerToggle toggleSearch = new ActionBarDrawerToggle(
                this, drawer, toolbarSearch, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.setDrawerListener(toggleSearch);
        toggle.syncState();
        toggleSearch.syncState();
        FirebaseInstanceId.getInstance().getToken();
        fragmentManager = getSupportFragmentManager();
        mSessionManager=new SessionManager(getApplicationContext());
        fragmentManager.beginTransaction().replace(R.id.fragment, new InterestedFragment()).commit();
        NavigationView navigationViewTop = (NavigationView) findViewById(R.id.nav_view1);
        navigationViewTop.setNavigationItemSelectedListener(this);
        setRightIcon(navigationViewTop);
        NavigationView navigationViewBottom = (NavigationView)findViewById(R.id.nav_view2);
        navigationViewBottom.setNavigationItemSelectedListener(this);
        Button becomePro= (Button) findViewById(R.id.becomepro);
        Button invite= (Button) findViewById(R.id.invite);
        becomePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChoicePackedCoin=new Intent(MainActivity.this,ChoicePackedCoinActivity.class);
                startActivity(intentChoicePackedCoin);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleChat.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.GONE);
                hideToolBarSearch();
                fragmentManager.beginTransaction().replace(R.id.fragment, new InviteFragment()).commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        // information header

        mTitleChat.setVisibility(View.GONE);
        View headerView = navigationViewTop.getHeaderView(0);
        mSettingProfile = (TextView) headerView.findViewById(R.id.setting_profile);

        mImgAvatar = (ImageView) headerView.findViewById(R.id.img_avatar_profile);
        ImageView look= (ImageView) headerView.findViewById(R.id.look);
        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.fragment, new SearchMainFragment()).commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        Picasso.with(this).load(Configuration.UPLOAD_IMAGE_URL+UserLogin.getUserLogin().getAvatarUrl())
                            .placeholder(R.drawable.avatar_friends)
                            .into(mImgAvatar);

        mSettingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileSettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        ImageView btnSetting = (ImageView) headerView.findViewById(R.id.btn_setting);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileSettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        mUsernameLogin = (TextView) headerView.findViewById(R.id.username_profile);
        if(UserLogin.getUserLogin().getUsername()!=null)
        mUsernameLogin.setText(UserLogin.getUserLogin().getUsername());
        else  mUsernameLogin.setText(UserLogin.getUserLogin().getFirstName() + UserLogin.getUserLogin().getLastName());

        //end

    }

    public void setRightIcon(NavigationView navigationView) {
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setActionView(R.layout.menu_dot);
        }
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
         hideToolBarSearch();
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_interested:
                mTitleChat.setVisibility(View.GONE);
                mImgFilter.setImageDrawable(getResources().getDrawable(R.drawable.filter));
                mImageView.setImageDrawable(getResources().getDrawable(R.drawable.logo_toolbar));
                fragment = new InterestedFragment();
                break;
            case R.id.nav_filter_vc:
                mTitleChat.setText(getResources().getString(R.string.item_nav_filter));
                mTitleChat.setVisibility(View.VISIBLE);
                setDefaultToolbar();
                fragment = new FilterFragment();
                break;
            case R.id.nav_chat:
                mTitleChat.setVisibility(View.VISIBLE);
                mTitleChat.setText("JSQMessages");
                mImageView.setImageDrawable(null);
                mImgFilter.setImageDrawable(null);
                fragment = new ChatFireBaseFragment();
                break;
            case R.id.nav_meet:
                mTitleChat.setText(getResources().getString(R.string.item_nav_meet));
                mTitleChat.setVisibility(View.VISIBLE);
                mImageView.setImageDrawable(null);
                mImgFilter.setImageDrawable(getResources().getDrawable(R.drawable.filter));
                fragment = new MeetMainFragment();
                break;
            case R.id.nav_profiles:
                mTitleChat.setText(getResources().getString(R.string.item_nav_profiles));
                mTitleChat.setVisibility(View.VISIBLE);
                setDefaultToolbar();
                fragment = new VisitorFragment();
                break;
            case R.id.nav_notification:
                mTitleChat.setVisibility(View.GONE);
                break;
            case R.id.nav_logout:
                mSessionManager.logoutUser();
                mTitleChat.setVisibility(View.GONE);
                ToolsActivity.logOut(this);
                return true;
            case R.id.nav_who_like:
                mTitleChat.setText(getResources().getString(R.string.item_nav_who_like));
                mTitleChat.setVisibility(View.VISIBLE);
                setDefaultToolbar();
                fragment = new WhoLikeMeFragment();
                break;

        }

        fragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        UserLogin.setUserLogin(null);
        Intent i = new Intent(MainActivity.this, SplashActivity.class);
        LoginManager.getInstance().logOut();
        startActivity(i);
        finish();
        return true;
    }

    private void setDefaultToolbar(){
        mImageView.setImageDrawable(null);
        mImgFilter.setImageDrawable(null);
    }
    public void hideToolBarSearch(){
        toolbar.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(View.GONE);
    }
}
