package com.signatic.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.signatic.adapter.RecycleCommentAdapter;
import com.signatic.cupid.R;
import com.signatic.model.Comment;
import com.signatic.model.User;
import com.signatic.utils.OnSwipeTouchListener;
import com.signatic.utils.UserLogin;
import com.signatic.utils.DividerItemDecoration;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NhutDu on 20/09/2016.
 */
public class ItemFragment extends Fragment {

    private RecycleCommentAdapter mAdapter;
    private ArrayList<Comment> mComments;
    private User mUser = null;

    public static Fragment newInstance(User user){
        ItemFragment fragment = new ItemFragment();
        fragment.mUser = user;
        return fragment;
    }

    @BindView(R.id.img_item_fragment)
    ImageView mImageView;
    @BindView(R.id.rv_cmt)
    RecyclerView mRecyclerView;
    @BindView(R.id.bt_comment)
    Button mButton;
    @BindView(R.id.et_content)
    EditText mEditText;
    @BindView(R.id.back_fragment_item)
    Button mBtnBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_fragment,container,false);
        ButterKnife.bind(this,view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mComments  = new ArrayList<>();
        mComments = addComment();
        Log.d("TAG user",mUser+"");
        mAdapter = new RecycleCommentAdapter(getContext(),mComments);
//        mRecyclerView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditText.getText().toString())){
                    mEditText.setError("Bạn phải nhập bình luận");
                    return;
                }else if(mEditText.getText().toString().length()<6){
                    mEditText.setError("Bình luận phải từ 6 ky tự trở lên");
                    return;
                }
                Comment comment = new Comment(UUID.randomUUID(),mEditText.getText().toString(), UserLogin.getUserLogin(),"nhut");
                mComments.add(comment);
                mEditText.setText("");
                Toast.makeText(getContext(),"Đăng bình luận thành công",Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        return view;

    }

    private ArrayList<Comment> addComment(){
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        comments.add(new Comment(UUID.randomUUID(),"bài đăng rất hay",new User("Lê Viết Nhựt","","",""),"nhut"));
        return comments;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mComments = null;
        mUser = null;
    }

}
