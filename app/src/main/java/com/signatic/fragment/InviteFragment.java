package com.signatic.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;
import com.signatic.adapter.InviteDividerItemDecoration;
import com.signatic.adapter.InviteItemAdapter;
import com.signatic.adapter.RecyclerItemClickListener;
import com.signatic.cupid.R;
import com.signatic.model.Friend;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

import static com.signatic.cupid.R.id.tab_friend;
import static com.signatic.cupid.R.id.tab_messages;
import static com.signatic.cupid.R.id.tab_store;

/**
 * Created by DefaultAccount on 10/28/2016.
 */
public class InviteFragment extends Fragment {
    private LayoutInflater mLayoutInflater;

    BottomBar bottomBar;

    FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater=inflater;

        View view=inflater.inflate(R.layout.fragment_invite,container,false);
        /*Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView mImageTitle = (ImageView) toolbar.findViewById(R.id.toolbar_title);
        toolbar.setVisibility(View.GONE);*/

        fragmentManager = getFragmentManager();
        //fragmentTransaction.replace(R.id.frameLayout,new FriendFragment()).commit();
        bottomBar= (BottomBar) view.findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                                             @Override
                                             public void onTabSelected(@IdRes int tabId) {
                                                 Fragment fragment = null;
                                                 switch (tabId) {
                                                     case tab_messages:
                                                         fragment = new MessageFragment();
                                                         break;
                                                     case tab_store:
                                                         fragment = new StoreFragment();
                                                         break;
                                                     default:
                                                         fragment = new FriendFragment();
                                                 }
                                                 fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                                             }
                                         });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

            }
        });
        return view;
    }


}
