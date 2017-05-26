package com.signatic.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import org.jetbrains.annotations.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.signatic.adapter.ChatFireBaseAdapter;
import com.signatic.cupid.R;
import com.signatic.model.Chat;
import com.signatic.utils.UserLogin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by DefaultAccount on 10/21/2016.
 */
public class ChatFireBaseFragment extends Fragment {
    LayoutInflater mLayoutInflater;
    ArrayList<Chat> messageArrayList;
    ChatFireBaseAdapter mChatAdapter;
    private Firebase mFirebaseRef;
    FirebaseDatabase mDataBase;
    FirebaseAuth mFirebaseAuth;
    StorageReference mStorageRef;
    FirebaseStorage mStorage;
    DatabaseReference mDataBaseReference;
    final static int SELECT_IMAGE=1;
    private String mId;
    private static final String TAG="Chat";
    String password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater=inflater;
        View view=mLayoutInflater.inflate(R.layout.chat_firebase,container,false);
        TextView title= (TextView) view.findViewById(R.id.title);
        final ImageView imageView= (ImageView) view.findViewById(R.id.icshare);
        TextView mSend= (TextView) view.findViewById(R.id.textsend);
        final EditText newMessage=(EditText)view.findViewById(R.id.newmessage);
        final RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.myList);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        messageArrayList=new ArrayList<Chat>();
        mId = Settings.Secure.getString(container.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        mChatAdapter=new ChatFireBaseAdapter(messageArrayList,mId,mLayoutInflater.getContext());
        mChatAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mChatAdapter);
        Firebase.setAndroidContext(container.getContext());
        mFirebaseRef = new Firebase("https://chat-bf3d1.firebaseio.com/").child("m");
        mDataBase = FirebaseDatabase.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();

        mDataBaseReference = mDataBase.getReference( "m" );

        mStorage = FirebaseStorage.getInstance();

        mStorageRef = mStorage.getReferenceFromUrl( "gs://chat-bf3d1.appspot.com/" );

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = newMessage.getText().toString();

                if (!message.isEmpty()) {
                    /**
                     * Firebase - Send message
                     */
                    if(UserLogin.getUserLogin().getUsername()==null){
                        mFirebaseRef.push().setValue(new Chat(message,mId,new Date(),UserLogin.getUserLogin().getFirstName(),null));

                    }
                    else
                    {
                        mFirebaseRef.push().setValue(new Chat(message,mId,new Date(),UserLogin.getUserLogin().getUsername(),null));

                    }
                }

                newMessage.setText("");
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(container.getContext(), R.style.MyPopupMenu);
                PopupMenu popup = new PopupMenu(wrapper,imageView);
                //Inflating the Popup using xml file
                popup.inflate(R.menu.popupmenu);
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id=item.getItemId();
                        switch (id){
                            case R.id.sendphoto:
                                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                               // getIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                                
                                getIntent.setType("image/*");

                                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                pickIntent.setType("image/*");

                                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                                startActivityForResult(chooserIntent, SELECT_IMAGE);
                                return true;
                        }
                        return false;
                    }
                });
            }

        });

        if(null==UserLogin.getUserLogin().getPassword()){
             password="cupid";
        }
        else password=UserLogin.getUserLogin().getPassword();
        mFirebaseRef.createUser(UserLogin.getUserLogin().getEmail(),password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
            }

            @Override
            public void onError(FirebaseError firebaseError) {

            }
        });
        mFirebaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    try{
                        Chat model = dataSnapshot.getValue(Chat.class);
                        messageArrayList.add(model);
                        mRecyclerView.scrollToPosition(messageArrayList.size() - 1);
                        mChatAdapter.notifyItemInserted(messageArrayList.size() - 1);

                    } catch (Exception ex) {
                        Log.e(TAG, ex.getMessage());
                    }
                }else{
                    System.out.println("chet cha dataSnapshot la null ");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return view;
    }
    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        if ( requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK )
        {
            Bitmap imageBitmap = null;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadPostTask uploadPostTask = new UploadPostTask();
            uploadPostTask.execute( imageBitmap );
        }
    }
    class UploadPostTask
            extends AsyncTask<Bitmap, Void, Void>
    {

        @Override
        protected Void doInBackground( Bitmap... params )
        {
            Bitmap bitmap = params[0];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress( Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream );
            mStorageRef.child( UUID.randomUUID().toString() + "jpg" ).putBytes(
                    byteArrayOutputStream.toByteArray() ).addOnSuccessListener(
                    new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess( UploadTask.TaskSnapshot taskSnapshot )
                        {
                            if ( taskSnapshot.getDownloadUrl() != null )
                            {
                                String imageUrl = taskSnapshot.getDownloadUrl().toString();
                                final Chat chat= new Chat(null,mId,new Date(),UserLogin.getUserLogin().getUsername(),imageUrl);
                                mFirebaseRef.push().setValue(chat);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        messageArrayList.add(chat);
                                        mChatAdapter.notifyDataSetChanged();
                                    }
                                });
                            }

                        }
                    }

            );
            mStorageRef.child( UUID.randomUUID().toString() + "jpg" ).putBytes(
                    byteArrayOutputStream.toByteArray() ).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NotNull Exception e) {
                    Log.e("Loi",e.toString());
                }
            });
            return null;
        }
    }
}
