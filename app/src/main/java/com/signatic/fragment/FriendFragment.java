package com.signatic.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.signatic.adapter.InviteDividerItemDecoration;
import com.signatic.adapter.InviteItemAdapter;
import com.signatic.adapter.InviteItemContractAdapter;
import com.signatic.adapter.RecyclerItemClickListener;
import com.signatic.cupid.R;
import com.signatic.model.Friend;
import com.signatic.model.User;
import com.signatic.model.UserInterested;
import com.signatic.service.services.InteredUserimpl;
import com.signatic.utils.Callback;
import com.signatic.utils.UserLogin;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by root on 08/11/2016.
 */

public class FriendFragment extends Fragment {
    private LayoutInflater mLayoutInflater;
    private RecyclerView mDsFriendOffline;
    private ArrayList<UserInterested> mFriends;
    private InviteItemAdapter mInviteItemAdapter;
    private InviteItemContractAdapter mInviteItemContractAdapter;
    private ArrayList<Friend> mContract;
    private EditText mSearch;
    private TextView mEdit,mFriendRequest;
    private RecyclerView mDsContract;
    String mName;
    String mPhone;
    private boolean loading=true;
    LinearLayoutManager mLinearLayoutManager;
    LinearLayoutManager mLinearLayoutManagercontract;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater=inflater;
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView textView= (TextView) toolbar.findViewById(R.id.chat);
        ImageView imgFilter = (ImageView) toolbar.findViewById(R.id.filter_interested);
        textView.setText("Friends");
        textView.setTextSize(16);
        imgFilter.setImageDrawable(getResources().getDrawable(R.drawable.addfr));
        View view=inflater.inflate(R.layout.friend_fragment,container,false);
        mDsFriendOffline= (RecyclerView) view.findViewById(R.id.dsFriendOffline);
        mDsFriendOffline.setNestedScrollingEnabled(false);
        mSearch= (EditText) view.findViewById(R.id.search);
        mFriendRequest=(TextView)view.findViewById(R.id.friendRequest);
        mEdit= (TextView) view.findViewById(R.id.edit);
        mFriends=new ArrayList<>();
        mLinearLayoutManager=new LinearLayoutManager(container.getContext());
        final InteredUserimpl interedUserimpl=new InteredUserimpl(inflater.getContext());
        interedUserimpl.getInteredUsers(UserLogin.getUserLogin(),page, new Callback<ArrayList<UserInterested>>() {
            @Override
            public void next(ArrayList<UserInterested> result) {
                mFriends=result;
                SetUpRecycleView(mLinearLayoutManager,mFriends,false);
                mInviteItemAdapter.notifyDataSetChanged();
            }
        });

        /*mDsFriendOffline.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                System.out.println("tau dang scroll do");
                page++;
                interedUserimpl.getInteredUsers(UserLogin.getmUserLogin().getLatitude(), UserLogin.getmUserLogin().getLongtitude(), UserLogin.getmUserLogin().getApiKey(),page, new Callback<ArrayList<UserInterested>>() {
                    @Override
                    public void next(ArrayList<UserInterested> result) {
                        mFriends.addAll(result);
                        SetUpRecycleView(mLinearLayoutManager,mFriends,false);
                        mInviteItemAdapter.notifyDataSetChanged();
                    }
                });
                if(dy >= page*mDsFriendOffline.getHeight()) //check for scroll down
                {
                    page++;
                    interedUserimpl.getInteredUsers(UserLogin.getmUserLogin().getLatitude(), UserLogin.getmUserLogin().getLongtitude(), UserLogin.getmUserLogin().getApiKey(),page, new Callback<ArrayList<UserInterested>>() {
                        @Override
                        public void next(ArrayList<UserInterested> result) {
                            mFriends.addAll(result);
                            SetUpRecycleView(mLinearLayoutManager,mFriends,false);
                            mInviteItemAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });*/
        mContract=new ArrayList<>();
        mDsContract=(RecyclerView) view.findViewById(R.id.dsContract);
        mDsContract.addOnItemTouchListener(new RecyclerItemClickListener(mLayoutInflater.getContext(),mDsContract,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                sendSMSMessage(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mLinearLayoutManagercontract=new LinearLayoutManager(container.getContext());
        mDsContract.setNestedScrollingEnabled(false);
        SetUpRecycleViewContract(mLinearLayoutManagercontract,mContract,mDsContract);
        getNumberPhoneContract(getActivity().getContentResolver());
        getEvent();
        return view;

    }

    private void SetUpRecycleView(LinearLayoutManager linearLayoutManager,ArrayList<UserInterested> friends,boolean edit) {
        mDsFriendOffline.setLayoutManager(linearLayoutManager);
        mDsFriendOffline.setItemAnimator(new DefaultItemAnimator());
        if(edit)
        {
            if(mEdit.getText().toString().equals("Save"))
                     mInviteItemAdapter=new InviteItemAdapter(friends,true);
            else  mInviteItemAdapter=new InviteItemAdapter(friends,false);
        }else mInviteItemAdapter=new InviteItemAdapter(friends,false);
        mDsFriendOffline.setAdapter(mInviteItemAdapter);
        mDsFriendOffline.addOnItemTouchListener(new RecyclerItemClickListener(mLayoutInflater.getContext(), mDsFriendOffline, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        mDsFriendOffline.addItemDecoration(new InviteDividerItemDecoration(mLayoutInflater.getContext()));
    }
    private void SetUpRecycleViewContract(LinearLayoutManager linearLayoutManager,ArrayList<Friend> friends,RecyclerView mDsRecycleView) {
        mDsRecycleView.setLayoutManager(linearLayoutManager);
        mDsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mInviteItemContractAdapter=new InviteItemContractAdapter(friends);
        mDsRecycleView.setAdapter(mInviteItemContractAdapter);
        mDsRecycleView.addItemDecoration(new InviteDividerItemDecoration(mLayoutInflater.getContext()));
    }

    public void getEvent(){
        final String arr[]=new String[mContract.size()];
        for(int i=0;i<arr.length;i++){
            arr[i]=mContract.get(i).getUrlImage();
        }
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdit.getText().toString().equals("Edit")){
                    mEdit.setText("Save");
                    SetUpRecycleView(mLinearLayoutManager,mFriends,true);
                }else{
                    mEdit.setText("Edit");
                    SetUpRecycleView(mLinearLayoutManager,mFriends,false);
                }
            }
        });
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                final Dialog dialog=new Dialog(mLayoutInflater.getContext(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                //dialog.setContentView(R.layout.dialog_search);
                LayoutInflater inflater = (LayoutInflater)mLayoutInflater.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View post = inflater.inflate(R.layout.dialog_search, null);
                final AutoCompleteTextView search= (AutoCompleteTextView) post.findViewById(R.id.startSearch);
                search.setAdapter(new ArrayAdapter<>(mLayoutInflater.getContext(), android.R.layout.select_dialog_item,arr));
                search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       sendSMSMessage(i);
                    }
                });
                LinearLayout outSide= (LinearLayout) post.findViewById(R.id.outside);
                outSide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(post);
                dialog.show();
            }
        });
        mFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*get message unread and send from app*/

            }
        });
    }
    public void getNumberPhoneContract(ContentResolver cr){
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        // use the cursor to access the contacts
        while (phones.moveToNext())
        {
            mName=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            mPhone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            mContract.add(new Friend(mName,mPhone));
        }
        phones.close();


    }
    protected void sendSMSMessage(int position) {
        mPhone=mContract.get(position).getUserName();
        mName=mContract.get(position).getUrlImage();
        final String message="Bạn muốn có thật nhiều bạn bè , bạn đang độc thận bạn có muốn có một người yêu thật là xinh hãy tham gia CUPID ngay để thực hiện điều này";

        AlertDialog.Builder builder=new AlertDialog.Builder(mLayoutInflater.getContext());
        builder.setTitle("Gởi tin nhắn mơi");
        builder
                .setMessage("Bạn muốn gởi lời mời tới "+mName+" số điện thoại:"+mPhone)
                .setCancelable(false)
                .setPositiveButton("Đồng Ý",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id) {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(mPhone, null, message, null, null);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Không",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

}
