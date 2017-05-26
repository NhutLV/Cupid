package com.signatic.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class User {

    //region Properties

    @SerializedName("id")
    private int mID;

    @SerializedName("username")
    private String mUsername;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("photo")
    private String mPhoto;

    @SerializedName("social_id")
    private String mSocialID;

    @SerializedName("status")
    private int mStatus;

    @SerializedName("interested_in")
    private int mInterestedIn;

    @SerializedName("api_key")
    private String mApiKey;

    @SerializedName("role")
    private String mRole;

    @SerializedName("comment")
    private Comment mComment;

    @SerializedName("is_male")
    private int mGender;

    @SerializedName("has_avatar")
    private int mHasAvatar;

    @SerializedName("avatar")
    private String mAvatarUrl;

    @SerializedName("popularity")
    private int mPopularity;

    @SerializedName("popular_purchased_date")
    private Date mPopularPurchasedDate;

    @SerializedName("age")
    private int mAge;

    @SerializedName("app_id")
    private int mAppID;

    @SerializedName("country_code")
    private String mCountryCode;

    @SerializedName("longtitude")
    private double mLongtitude;

    @SerializedName("latitude")
    private double mLatitude;

    @SerializedName("last_online")
    private Date mLastOnline;

    @SerializedName("is_actived")
    private int mActive;

    @SerializedName("reports")
    private int mReports;

    @SerializedName("token")
    private String mToken;

    @SerializedName("snap_username")
    private String mSnapUsername;

    @SerializedName("kik_username")
    private String mKikUsername;

    @SerializedName("skype_username")
    private String mSkypeUsername;

    @SerializedName("instagram_username")
    private String mInstagramUsername;

    @SerializedName("oovoo_username")
    private String mOovooUsername;

    @SerializedName("display_name")
    String mDisplayName;

    @SerializedName("who_can_see_me")
    int mWhoCanSeeMe;

    @SerializedName("age_range_can_see_me_from")
    int mAgeRangeFrom;

    @SerializedName("age_range_can_see_me_to")
    int mAgeRangeTo;

    @SerializedName("push_notification")
    int mNotification;

    @SerializedName("earn_coins")
    int mEarnCoins;

    @SerializedName("i_am_here_to")
    int mIAmHereTo;

    @SerializedName("relationship")
    int mRelationShip;

    @SerializedName("drinking")
    int mDrinking;

    @SerializedName("smoking")
    int mSmoking;

    @SerializedName("living")
    int mLiving;

    @SerializedName("kids")
    int mKids;

    @SerializedName("education")
    int mEducation;

    @SerializedName("job")
    String mJob;

    @SerializedName("company")
    String mCompany;

    @SerializedName("vip_days")
    int mVipDays;

    @SerializedName("vip_upgrade")
    Date mVipUpgrade;

    @SerializedName("tags")
    ArrayList<TagClass> mTags;

    //endregion

    //region Getter and Setter

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getSocialID() {
        return mSocialID;
    }

    public void setSocialID(String socialID) {
        mSocialID = socialID;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(String role) {
        mRole = role;
    }

    public Comment getComment() {
        return mComment;
    }

    public void setComment(Comment comment) {
        mComment = comment;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public int getHasAvatar() {
        return mHasAvatar;
    }

    public void setHasAvatar(int hasAvatar) {
        mHasAvatar = hasAvatar;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public int getPopularity() {
        return mPopularity;
    }

    public void setPopularity(int popularity) {
        mPopularity = popularity;
    }

    public Date getPopularPurchasedDate() {
        return mPopularPurchasedDate;
    }

    public void setPopularPurchasedDate(Date popularPurchasedDate) {
        mPopularPurchasedDate = popularPurchasedDate;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public int getAppID() {
        return mAppID;
    }

    public void setAppID(int appID) {
        mAppID = appID;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public double getLongtitude() {
        return mLongtitude;
    }

    public void setLongtitude(double longtitude) {
        mLongtitude = longtitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public Date getLastOnline() {
        return mLastOnline;
    }

    public void setLastOnline(Date lastOnline) {
        mLastOnline = lastOnline;
    }

    public int getActive() {
        return mActive;
    }

    public void setActive(int active) {
        mActive = active;
    }

    public int getReports() {
        return mReports;
    }

    public void setReports(int reports) {
        mReports = reports;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getSnapUsername() {
        return mSnapUsername;
    }

    public void setSnapUsername(String snapUsername) {
        mSnapUsername = snapUsername;
    }

    public String getSkypeUsername() {
        return mSkypeUsername;
    }

    public void setSkypeUsername(String skypeUsername) {
        mSkypeUsername = skypeUsername;
    }

    public String getKikUsername() {
        return mKikUsername;
    }

    public void setKikUsername(String mKikUsername) {
        this.mKikUsername = mKikUsername;
    }

    public String getInstagramUsername() {
        return mInstagramUsername;
    }

    public void setInstagramUsername(String mInstagramUsername) {
        this.mInstagramUsername = mInstagramUsername;
    }

    public int getInterestedIn() {
        return mInterestedIn;
    }

    public void setInterestedIn(int mInterestedIn) {
        this.mInterestedIn = mInterestedIn;
    }

    public String getOovooUsername() {
        return mOovooUsername;
    }

    public void setOovooUsername(String oovooUsername) {
        mOovooUsername = oovooUsername;
    }

    public ArrayList<TagClass> getTags() {
        if(mTags==null){
            mTags = new ArrayList<>();
        }
        return mTags;
    }

    public void setTags(ArrayList<TagClass> mTags) {
        this.mTags = mTags;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public int getWhoCanSeeMe() {
        return mWhoCanSeeMe;
    }

    public void setWhoCanSeeMe(int whoCanSeeMe) {
        this.mWhoCanSeeMe = whoCanSeeMe;
    }

    public int getAgeRangeFrom() {
        return mAgeRangeFrom;
    }

    public void setAgeRangeFrom(int ageRangeFrom) {
        mAgeRangeFrom = ageRangeFrom;
    }

    public int getAgeRangeTo() {
        return mAgeRangeTo;
    }

    public void setAgeRangeTo(int ageRangeTo) {
        mAgeRangeTo = ageRangeTo;
    }

    public int getNotification() {
        return mNotification;
    }

    public void setNotification(int notification) {
        mNotification = notification;
    }

    public int getEarnCoins() {
        return mEarnCoins;
    }

    public void setEarnCoins(int earnCoins) {
        mEarnCoins = earnCoins;
    }

    public Date getVipUpgrade() {
        return mVipUpgrade;
    }

    public void setVipUpgrade(Date vipUpgrade) {
        mVipUpgrade = vipUpgrade;
    }

    public int getIAmHereTo() {
        return mIAmHereTo;
    }

    public void setIAmHereTo(int IAmHereTo) {
        mIAmHereTo = IAmHereTo;
    }

    public int getRelationShip() {
        return mRelationShip;
    }

    public void setRelationShip(int relationShip) {
        mRelationShip = relationShip;
    }

    public int getDrinking() {
        return mDrinking;
    }

    public void setDrinking(int drinking) {
        mDrinking = drinking;
    }

    public int getSmoking() {
        return mSmoking;
    }

    public void setSmoking(int smoking) {
        mSmoking = smoking;
    }

    public int getLiving() {
        return mLiving;
    }

    public void setLiving(int living) {
        mLiving = living;
    }

    public int getKids() {
        return mKids;
    }

    public void setKids(int kids) {
        mKids = kids;
    }

    public int getEducation() {
        return mEducation;
    }

    public void setEducation(int education) {
        mEducation = education;
    }

    public String getJob() {
        return mJob;
    }

    public void setJob(String job) {
        mJob = job;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public int getVipDays() {
        return mVipDays;
    }

    public void setVipDays(int vipDays) {
        mVipDays = vipDays;
    }

    //endregion

    //region Constructor


    public User() {
    }

    public User(String firstName, String lastName, String socialID, String email) {
        mTags = new ArrayList<>();
        mFirstName = firstName;
        mLastName = lastName;
        mSocialID = socialID;
        mEmail = email;
    }

    public User(String username, String firstName, String lastName, String email, String socialID, String avatarUrl) {
        mTags = new ArrayList<>();
        mUsername = username;
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mSocialID = socialID;
        mAvatarUrl = avatarUrl;
    }

    public User(String firstName, String lastName, String email, String password, int age, String avatarUrl, double latitude, double longtitude, String countryCode,int gender) {
        mTags = new ArrayList<>();
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mPassword = password;
        mAge = age;
        mAvatarUrl = avatarUrl;
        mLatitude = latitude;
        mLongtitude = longtitude;
        mCountryCode = countryCode;
        mGender = gender;
    }

    //endregion

    //region toString

    @Override
    public String toString() {
        return "User{" +
                "id "+mID+
                "mEmail='" + mEmail + '\'' +
                ", mNotification=" + mNotification +
                ", mEarnCoins=" + mEarnCoins +
                ", mVipUpgrade=" + mVipUpgrade +
                ", mWhoCanSeeMe=" + mWhoCanSeeMe +
                ", mID=" + mID +
                '}';
    }


    //endregion
}
