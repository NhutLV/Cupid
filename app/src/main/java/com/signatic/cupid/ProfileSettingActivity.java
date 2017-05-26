package com.signatic.cupid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.TagView;
import com.rey.material.widget.Switch;
import com.signatic.adapter.RecycleAccountAdapter;
import com.signatic.adapter.RecyclerItemClickListener;
import com.signatic.library.horizontalpicker.HorizontalPicker;
import com.signatic.model.User;
import com.signatic.service.Configuration;
import com.signatic.service.services.UserService;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.Callback;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;
import com.signatic.utils.Utils;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by NhutDu on 03/10/2016.
 */
public class ProfileSettingActivity extends AppCompatActivity{

    @BindView(R.id.rv_profile_setting)
    RecyclerView mRecyclerView;

    RecycleAccountAdapter mAdapter;

    @BindView(R.id.edit_profile)
    TextView mEditProfile;

    @BindView(R.id.back_profile)
    TextView mBackProfile;

    @BindView(R.id.name_profile)
    EditText mName;

    @BindView(R.id.status_profile)
    EditText mStatus;

    @BindView(R.id.et_profile_snapchat)
    EditText mSnapChat;

    @BindView(R.id.et_profile_kik)
    EditText mKik;

    @BindView(R.id.et_profile_instagram)
    EditText mInstagram;

    @BindView(R.id.et_profile_oovoo)
    EditText mOovoo;

    @BindView(R.id.et_profile_skype)
    EditText mSkype;

    @BindView(R.id.et_profile_viber)
    EditText mViber;

    @BindView(R.id.et_profile_displayname)
    EditText mDisplayName;

    @BindView(R.id.bt_profile_tags)
    Button mNavigateTags;

    @BindView(R.id.picker_age_profile)
    HorizontalPicker mAgePicker;

    @BindView(R.id.picker_age_range_profile1)
    HorizontalPicker mAgeRangeFromPicker;

    @BindView(R.id.picker_age_range_profile2)
    HorizontalPicker mAgeRangeToPicker;

    @BindView(R.id.switch_notify_profile)
    Switch mNotify;

    @BindView(R.id.spinner_gender)
    Spinner mGender;

    @BindView(R.id.spinner_who_see)
    Spinner mWhoSee;

    @BindView(R.id.spinner_interested)
    Spinner mInterested;

    @BindView(R.id.spinner_i_am_here_to)
    Spinner mIAmHereTo;

    @BindView(R.id.spinner_relationship)
    Spinner mRelationship;

    @BindView(R.id.spinner_drinking)
    Spinner mDrinking;

    @BindView(R.id.spinner_living)
    Spinner mLiving;

    @BindView(R.id.spinner_smoking)
    Spinner mSmoking;

    @BindView(R.id.spinner_kids)
    Spinner mKids;

    @BindView(R.id.spinner_education)
    Spinner mEducation;

    @BindView(R.id.et_profile_job)
    EditText mJob;

    @BindView(R.id.et_profile_company)
    EditText mCompany;

    @BindView(R.id.tag_view_content_profile)
    TagView mTags;

    @BindView(R.id.profile_image_avatar)
    CircleImageView mAvatar;

    @BindView(R.id.profile_edit_image)
    ImageView mEditAvatar;

    @BindView(R.id.profile_image_cover)
    ImageView mCover;

    @BindView(R.id.profile_edit_cover)
    ImageView mEditCover;

    User user = UserLogin.getUserLogin();

    Uri mUriAvatar ;
    String mUrlAvatar = user.getAvatarUrl();

    Uri mUriCover ;
    String mUrlCover = user.getPhoto();

    private static final int PICK_PHOTO_FOR_AVATAR= 1;
    private static final int PICK_PHOTO_FOR_COVER= 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolsActivity.setColorStatus(this,R.color.colorPrimary);
        setContentView(R.layout.activity_profile_setting);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);

        setSupportActionBar(toolbar);
        setDefaultEditText();

        mBackProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelProfileActivity();
            }
        });

        CharSequence [] ages = getResources().getStringArray(R.array.values_age);
        final int age[] = new int[ages.length];
        for(int i=0;i<ages.length;i++){
            age[i] = Integer.parseInt(ages[i].toString());
        }

        final CharSequence[] ageTos = new CharSequence[age.length];
        final int ageTo []= new int[age.length];

        setUpSpinner(mGender,R.array.gender);
        setUpSpinner(mWhoSee,R.array.who_see);
        setUpSpinner(mInterested,R.array.interested);
        setUpSpinner(mIAmHereTo,R.array.i_am_here_to);
        setUpSpinner(mDrinking,R.array.drinking);
        setUpSpinner(mSmoking,R.array.smoking);
        setUpSpinner(mLiving,R.array.living);
        setUpSpinner(mEducation,R.array.education);
        setUpSpinner(mKids,R.array.kids);
        setUpSpinner(mRelationship,R.array.relationship);

        setValueField(mName,getResources().getString(R.string.username),user.getFirstName()+user.getLastName());
        setValueField(mDisplayName,getResources().getString(R.string.profile_display_name),user.getDisplayName());
        setValueField(mSnapChat,getResources().getString(R.string.profile_snapchat), user.getSnapUsername());
        setValueField(mKik,getResources().getString(R.string.profile_kik), user.getKikUsername());
        setValueField(mInstagram,getResources().getString(R.string.profile_instagram), user.getInstagramUsername());
        setValueField(mOovoo,getResources().getString(R.string.ooovoo), user.getOovooUsername());
        setValueField(mSkype,getResources().getString(R.string.skype),user.getSkypeUsername());
        setValueField(mViber,getResources().getString(R.string.viber),user.getKikUsername());
        setValueField(mJob,getResources().getString(R.string.job),user.getJob());
        setValueField(mCompany,getResources().getString(R.string.company),user.getCompany());
        setValuesSelectPicker(mAgePicker,user.getAge());
        setValuesSelectPicker(mAgeRangeFromPicker,user.getAgeRangeFrom());
        setValuesSelectPicker(mAgeRangeToPicker,user.getAgeRangeTo());

        Picasso.with(this)
                .load(Configuration.UPLOAD_IMAGE_URL+user.getAvatarUrl())
                .placeholder(R.drawable.avatar_friends2x)
                .into(mAvatar);
        Picasso.with(this)
                .load(Configuration.UPLOAD_IMAGE_URL+user.getPhoto())
                .placeholder(R.drawable.image)
                .into(mCover);


        // add list tag of user to profile view
        Utils.addTagsToView(mTags,user.getTags(),false);

        mGender.setSelection(user.getGender());
        mInterested.setSelection(user.getInterestedIn());
        mIAmHereTo.setSelection(user.getIAmHereTo());
        mDrinking.setSelection(user.getDrinking());
        mSmoking.setSelection(user.getSmoking());
        mLiving.setSelection(user.getLiving());
        mKids.setSelection(user.getKids());
        mEducation.setSelection(user.getEducation());
        mRelationship.setSelection(user.getRelationShip());

        if(user.getNotification()==1){
            mNotify.setChecked(true);
        }else{
            mNotify.setChecked(false);
        }

        mAdapter = new RecycleAccountAdapter(getApplicationContext(),getAccounts());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        //disable scroll item recycle in nested scroll
        mRecyclerView.setNestedScrollingEnabled(false);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position){
                    case 0:
                        startActivity(new Intent(ProfileSettingActivity.this,ChoicePackedCoinActivity.class));
                        ProfileSettingActivity.this.overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        break;
                    case 1:
                        startActivity(new Intent(ProfileSettingActivity.this,EnjoyDayActivity.class));
                        ProfileSettingActivity.this.overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        break;
                    case 2:
                        shareApp();
                        break;
                    case 3:
                        startActivity(new Intent(ProfileSettingActivity.this,TermOfUserActivity.class));
                        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        break;
                    case 4:
                        goSupport();
                        break;
                    case 5:
                        break;
                    case 6:
                        ToolsActivity.logOut(ProfileSettingActivity.this);
                        break;
                    case 7:
                        deleteUser();
                        break;
                }

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        // navigate to tags profile activity
        mNavigateTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileSettingActivity.this,TagsProfileActivity.class));
                finish();
                ProfileSettingActivity.this.overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

        mEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditProfile.getText().toString().equalsIgnoreCase(getResources().getString(R.string.edit))){

                    setEnableView();
                    mEditProfile.setText("Save");

                }else if(mEditProfile.getText().toString().equalsIgnoreCase(getResources().getString(R.string.save))){

                    mEditProfile.setText("Edit");
                    saveInfoProfile();
                    setDisableView();

                }
            }
        });

        showPopmenu();

        // pending scroll ageTo
        mAgeRangeFromPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0,j=0;i<age.length;i++){
                    if(getValuePickerAtIndex(mAgeRangeFromPicker,mAgeRangeFromPicker.getSelectedItem())<age[i]){
                        ageTo [j] = age[i];
                        j++;
                    }
                }

                for(int i=0;i<ageTo.length;i++){
                    ageTos[i] =ageTo[i]+"";
                }

                mAgeRangeToPicker.setValues(ageTos);
            }
        });



    }

    //region Private methods

    private void setValueField(EditText editText, String field, String value){

        if(TextUtils.isEmpty(value)){
            editText.setHint(Html.fromHtml("<i> "+ getResources().getString(R.string.enter)+ field + "</i>"));
        }
        else{
            editText.setText(value);
        }
    }

    private ArrayList<String> getAccounts(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getResources().getString(R.string.earn_coins));
        arrayList.add(getResources().getString(R.string.vip_upgrade));
        arrayList.add(getResources().getString(R.string.share_add_me));
        arrayList.add(getResources().getString(R.string.term_of_use));
        arrayList.add(getResources().getString(R.string.support));
        arrayList.add(getResources().getString(R.string.restore_purchases));
        arrayList.add(getResources().getString(R.string.action_logout));
        arrayList.add(getResources().getString(R.string.del_account));

        return arrayList;
    }

    private void setEnable(View editText){
        editText.setEnabled(true);
    }

    private void setDisable(View editText){
        editText.setEnabled(false);
    }

    private void setEnableView(){
        setEnable(mName);
        setEnable(mStatus);
        setEnable(mSnapChat);
        setEnable(mKik);
        setEnable(mOovoo);
        setEnable(mViber);
        setEnable(mDisplayName);
        setEnable(mSkype);
        setEnable(mNavigateTags);
        setEnable(mInstagram);
        setEnable(mInterested);
        setEnable(mAgePicker);
        setEnable(mAgeRangeFromPicker);
        setEnable(mAgeRangeToPicker);
        setEnable(mNotify);
        setEnable(mGender);
        Utils.enableView(mNotify);
        Utils.enableView(mRecyclerView);
        setEnable(mWhoSee);
        setEnable(mIAmHereTo);
        setEnable(mDrinking);
        setEnable(mSmoking);
        setEnable(mLiving);
        setEnable(mKids);
        setEnable(mEducation);
        setEnable(mJob);
        setEnable(mCompany);
        setEnable(mRelationship);
        setEnable(mEditAvatar);
        setEnable(mEditCover);
    }

    private void setDisableView(){
        setDisable(mName);
        setDisable(mStatus);
        setDisable(mSnapChat);
        setDisable(mKik);
        setDisable(mOovoo);
        setDisable(mViber);
        setDisable(mDisplayName);
        setDisable(mSkype);
        setDisable(mNavigateTags);
        setDisable(mInstagram);
        setDisable(mInterested);
        setDisable(mAgeRangeFromPicker);
        setDisable(mAgeRangeToPicker);
        setDisable(mAgePicker);
        Utils.disableView(mNotify);
        Utils.disableView(mRecyclerView);
        setDisable(mGender);
        setDisable(mWhoSee);
        setDisable(mIAmHereTo);
        setDisable(mDrinking);
        setDisable(mSmoking);
        setDisable(mLiving);
        setDisable(mKids);
        setDisable(mEducation);
        setDisable(mJob);
        setDisable(mCompany);
        setDisable(mRelationship);
        setDisable(mEditAvatar);
        setDisable(mEditCover);
    }

    private void setDefaultEditText(){
        setDisableView();
    }

    private void setUpSpinner(Spinner spinner,int arrStringID){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrStringID, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setValuesSelectPicker(HorizontalPicker picker,int n){
        CharSequence []arr =picker.getValues();
        for(int i=0;i<arr.length;i++){
            if(Integer.parseInt(arr[i].toString())==n){
                picker.setSelectedItem(i);
            }
        }
    }

    private int getValuePickerAtIndex(HorizontalPicker picker, int index){
        int age =0;
        CharSequence []arr =picker.getValues();
        for(int i=0;i<arr.length;i++){
            if(i ==index) {
                age = Integer.parseInt(arr[index].toString());
            }
        }
        return age;

    }

    private void cancelProfileActivity(){
        startActivity(new Intent(this,MainActivity.class));
//        Toast.makeText(getApplicationContext(), getResources().getString(R.string.message_no_save_profile), Toast.LENGTH_LONG).show();
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancelProfileActivity();
    }

    private void saveInfoProfile(){
        String username = mName.getText().toString();
        String snap = mSnapChat.getText().toString();
        String kik = mKik.getText().toString();
        String instagram = mInstagram.getText().toString();
        String oovoo = mOovoo.getText().toString();
        String skype = mSkype.getText().toString();
        String displayname = mDisplayName.getText().toString();
        int age =  getValuePickerAtIndex(mAgePicker,mAgePicker.getSelectedItem());
        int gender = mGender.getSelectedItemPosition();
        int interested = mInterested.getSelectedItemPosition();
        int whoCanSee = mWhoSee.getSelectedItemPosition();
        int ageFrom = getValuePickerAtIndex(mAgeRangeFromPicker,mAgeRangeFromPicker.getSelectedItem());
        int ageTo = getValuePickerAtIndex(mAgeRangeToPicker,mAgeRangeToPicker.getSelectedItem());
        int notification = (mNotify.isChecked()?1:0);
        int iAmHereTo = mIAmHereTo.getSelectedItemPosition();
        int drinking = mDrinking.getSelectedItemPosition();
        int smoking = mSmoking.getSelectedItemPosition();
        int living = mLiving.getSelectedItemPosition();
        int relationship = mRelationship.getSelectedItemPosition();
        int kids = mKids.getSelectedItemPosition();
        int education = mEducation.getSelectedItemPosition();
        String job = mJob.getText().toString();
        String company = mCompany.getText().toString();

        User userUpdate = user;

        userUpdate.setUsername(username);
        userUpdate.setSnapUsername(snap);
        userUpdate.setKikUsername(kik);
        userUpdate.setInstagramUsername(instagram);
        userUpdate.setOovooUsername(oovoo);
        userUpdate.setSkypeUsername(skype);
        userUpdate.setInterestedIn(interested);
        userUpdate.setDisplayName(displayname);
        userUpdate.setGender(gender);
        userUpdate.setAge(age);
        userUpdate.setWhoCanSeeMe(whoCanSee);
        userUpdate.setNotification(notification);
        userUpdate.setAgeRangeFrom(ageFrom);
        userUpdate.setAgeRangeTo(ageTo);
        userUpdate.setJob(job);
        userUpdate.setCompany(company);
        userUpdate.setIAmHereTo(iAmHereTo);
        userUpdate.setDrinking(drinking);
        userUpdate.setSmoking(smoking);
        userUpdate.setLiving(living);
        userUpdate.setRelationShip(relationship);
        userUpdate.setEducation(education);
        userUpdate.setKids(kids);
        userUpdate.setAvatarUrl(mUrlAvatar);
        userUpdate.setPhoto(mUrlCover);


        UserServiceImpl userService = new UserServiceImpl(getApplication());
        userService.updateProfile(userUpdate, user, new Callback<User>() {
            @Override
            public void next(User result) {
                SessionManager session = new SessionManager(ProfileSettingActivity.this);
                session.createLoginSession(result);
            }
        });
    }

    public void pickImageAvatar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_FOR_AVATAR);
    }

    public void pickImageCover() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_FOR_COVER);
    }

    private void goSupport(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "You can send problem to us...");

        startActivity(Intent.createChooser(intent, "Send Support"));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case PICK_PHOTO_FOR_AVATAR:
                if(resultCode == RESULT_OK){
                    final Uri selectedImage = imageReturnedIntent.getData();
                    new UserServiceImpl(getApplicationContext()).uploadFile(selectedImage,user.getApiKey(), new Callback<String>() {
                        @Override
                        public void next(String result) {
                            if(!"error".equals(result)) {
                                mUrlAvatar = result;
                                mAvatar.setImageURI(selectedImage);
                                mUriAvatar =selectedImage;
                            }
                        }
                    });
                }
                break;
            case PICK_PHOTO_FOR_COVER:
                if(resultCode == RESULT_OK){
                    final Uri selectedImage = imageReturnedIntent.getData();
                    new UserServiceImpl(getApplicationContext()).uploadFile(selectedImage,user.getApiKey(), new Callback<String>() {
                        @Override
                        public void next(String result) {
                            if(!"error".equals(result)) {
                                mUrlCover = result;
                                mCover.setImageURI(selectedImage);
                                mUriCover =selectedImage;
                            }
                        }
                    });
                }
                break;
        }
    }

    private void shareApp(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>Hi, this is cupid app</p>"));
        startActivity(Intent.createChooser(sharingIntent,"Cupid"));
    }


    private void deleteUser(){
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        final UserServiceImpl userService = new UserServiceImpl();
                        userService.deleteUser(user.getID(), new Callback<Boolean>() {
                            @Override
                            public void next(Boolean result) {
                                if(result){
                                    Toast.makeText(getApplicationContext(),"You delete your account successfully !",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ProfileSettingActivity.this, SplashActivity.class);
                                    SessionManager sessionManager = new SessionManager(getApplicationContext());
                                    sessionManager.logoutUser();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    finishAffinity();
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Have a error when deleting account !",Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileSettingActivity.this);
        builder.setMessage("Are you sure buy a coin package?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    private void showPopmenu() {
        mEditAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ProfileSettingActivity.this, mEditAvatar);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.avatar_popmenu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_upload:
                                pickImageAvatar();
                                break;
                            case R.id.menu_update:
                                break;
                            case R.id.menu_delete:
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method


        mEditCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ProfileSettingActivity.this, mEditCover);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.cover_popmenu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_upload:
                                pickImageCover();
                                break;
                            case R.id.menu_update:
                                break;
                            case R.id.menu_delete:
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method
    }


    /*
    private void setDisableListView(List<View> views){

        for(View view:views){
            setDisable(view);
        }

    }

    private void setEnableListView(List<View> views){

        for(View view:views){
            setEnable(view);
        }

    }
    */
    //endregion

}
