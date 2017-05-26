package com.signatic.service.services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.signatic.cupid.LoginEmailActivity;
import com.signatic.cupid.MainActivity;
import com.signatic.cupid.ProfileSettingActivity;
import com.signatic.cupid.R;
import com.signatic.cupid.SessionManager;
import com.signatic.event.UserLikeMeEvent;
import com.signatic.event.UserNearbyEvent;
import com.signatic.event.UserVisitorEvent;
import com.signatic.model.User;
import com.signatic.service.Configuration;
import com.signatic.service.Response.APIResponse;
import com.signatic.service.Response.APIResponseUpload;
import com.signatic.service.Response.APIResponseUsers;
import com.signatic.utils.FileUtils;
import com.signatic.utils.PrefManager;
import com.signatic.utils.UserLogin;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NhutDu on 06/10/2016.
 */
public class UserServiceImpl extends BaseService<UserService> {

    //region Properties

    private SessionManager session;
    Context mContext;
    PrefManager mPrefManager;

    //endregion

    //region Constructor

    //region Constructor

    public UserServiceImpl(Context context) {
        mContext = context;
        mPrefManager = new PrefManager(mContext);
        session = new SessionManager(mContext);
    }

    public UserServiceImpl() {
    }

    //endregion

    //region Public methods

    //register with facebook
    public void registerFb(final User user) {
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.registerFb(user.getFirstName(), user.getLastName(), user.getEmail(), user.getSocialID());
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if (!apiResponse.isError()) {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    User userLogin = apiResponse.getData();
                    userLogin.setUsername(user.getUsername());
                    userLogin.setAvatarUrl(user.getAvatarUrl());
                    UserLogin.setUserLogin(userLogin);
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                UserLogin.setUserLogin(user);
                Log.d("TAG", "", t);
            }
        });
    }

    // register account in app
    public void register(User user) {
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.register(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                user.getLatitude(), user.getLongtitude(), user.getAge(), user.getAvatarUrl(),
                user.getCountryCode(), user.getGender());
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if (!apiResponse.isError()) {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    User userLogin = apiResponse.getData();
                    UserLogin.setUserLogin(userLogin);
                    Intent intent = new Intent(mContext, LoginEmailActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("TAG register", "", t);
            }
        });

    }

    // login
    public void login(final String email, final String password) {
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.login(email, password);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if (!apiResponse.isError()) {
                    Log.d("Api Key",apiResponse.getData().getApiKey());
                    User userLogin = apiResponse.getData();
                    UserLogin.setUserLogin(userLogin);
                    session.createLoginSession(userLogin);
                    if (mPrefManager.isFirstTime()) {
                        mPrefManager.setIsFristTime(false);
                        Intent intent = new Intent(mContext, ProfileSettingActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }

                } else {
                    Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("TAG Login", "", t);
            }
        });
    }

    // update information profile
    public void updateProfile(final User user, final User userOld, final com.signatic.utils.Callback<User> callback) {
        UserService userService = Configuration.getClient(user.getApiKey()).create(UserService.class);
        Call<APIResponse<User>> call = userService.updateProfile(user.getEmail(), user.getID(),
                user.getFirstName(), user.getLastName(), user.getGender(), user.getLatitude(),
                user.getLongtitude(), user.getAge(), user.getAvatarUrl(), user.getPhoto(),
                user.getSnapUsername(), user.getKikUsername(), user.getInstagramUsername(),
                user.getOovooUsername(), user.getSkypeUsername(), user.getInterestedIn(),
                user.getIAmHereTo(), user.getRelationShip(), user.getDrinking(), user.getSmoking(),
                user.getLiving(), user.getKids(), user.getEducation(), user.getJob(), user.getCompany(),
                user.getDisplayName(), user.getWhoCanSeeMe(), user.getAgeRangeFrom(), user.getAgeRangeTo(),
                user.getNotification(), user.getEarnCoins(), user.getVipUpgrade());
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if (!apiResponse.isError()) {
                    UserLogin.setUserLogin(user);
                    callback.next(user);
                } else {
                    UserLogin.setUserLogin(userOld);
                    callback.next(userOld);
                }
                Toast.makeText(mContext, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("TAG", "", t);
                callback.next(userOld);
            }
        });
    }

    // get users you was visited
    public void getVisitorUser(int id, final int page,String apiKey, final com.signatic.utils.Callback<ArrayList<User>> callback) {
        UserService userService = Configuration.getClient(apiKey).create(UserService.class);
        Call<APIResponseUsers> call = userService.getVisitorUsers(id, page);
        call.enqueue(new Callback<APIResponseUsers>() {
            @Override
            public void onResponse(Call<APIResponseUsers> call, Response<APIResponseUsers> response) {
                ArrayList<User> users = new ArrayList<User>();
                if (!response.body().isError()) {
                    if (page == 1) {
                        users = response.body().getUsers();
                    } else if (page > 1) {
                        users.addAll(response.body().getUsers());
                    }
                    callback.next(users);
                } else {
                    callback.next(users);
                }
            }

            @Override
            public void onFailure(Call<APIResponseUsers> call, Throwable t) {
                EventBus.getDefault().postSticky(new UserVisitorEvent(null));
            }
        });

    }

    //get users like me
    public void getUsersLikeMe(int id, final int page,String api, final com.signatic.utils.Callback<ArrayList<User>> callback) {
        UserService userService = Configuration.getClient(api).create(UserService.class);
        Call<APIResponseUsers> call = userService.getUsersLikeMe(id, page);
        call.enqueue(new Callback<APIResponseUsers>() {
            @Override
            public void onResponse(Call<APIResponseUsers> call, Response<APIResponseUsers> response) {
                ArrayList<User> users = new ArrayList<User>();
                if (!response.body().isError()) {
                    if (page == 1) {
                        users = response.body().getUsers();
                    } else if (page > 1) {
                        users.addAll(response.body().getUsers());
                    }
                    callback.next(users);
                } else {
                    callback.next(users);
                }
            }

            @Override
            public void onFailure(Call<APIResponseUsers> call, Throwable t) {
            }
        });

    }


    // get list nearby user
    public void getNearbyUsers(User user, final int page, final com.signatic.utils.Callback<ArrayList<User>> callback) {
        UserService userService = Configuration.getClient(user.getApiKey()).create(UserService.class);
        Call<APIResponseUsers> call = userService.getNearbyUsers(user.getLatitude(), user.getLongtitude(), user.getWhoCanSeeMe(), user.getAgeRangeFrom(), user.getAgeRangeTo(), user.getGender(), page);
        call.enqueue(new Callback<APIResponseUsers>() {
            @Override
            public void onResponse(Call<APIResponseUsers> call, Response<APIResponseUsers> response) {
                ArrayList<User> users = new ArrayList<User>();
                if (!response.body().isError()) {
                    if (page == 1) {
                        users = response.body().getUsers();
                    } else if (page > 1) {
                        users.addAll(response.body().getUsers());
                    }
                    callback.next(users);
                } else {
                    callback.next(users);
                }
            }

            @Override
            public void onFailure(Call<APIResponseUsers> call, Throwable t) {
                EventBus.getDefault().postSticky(new UserNearbyEvent(null));
            }
        });

    }

    //get list user global
    public void getGlobalUsers(final User user, final int page, final com.signatic.utils.Callback<ArrayList<User>> callback) {
        UserService userService = Configuration.getClient(user.getApiKey()).create(UserService.class);
        Call<APIResponseUsers> call = userService.getGlobalUsers(user.getLatitude(), user.getLongtitude(), user.getWhoCanSeeMe(), user.getAgeRangeFrom(), user.getAgeRangeTo(), user.getGender(), page);
        call.enqueue(new Callback<APIResponseUsers>() {
            @Override
            public void onResponse(Call<APIResponseUsers> call, Response<APIResponseUsers> response) {
                ArrayList<User> users = new ArrayList<User>();
                if (!response.body().isError()) {
                    if (page == 1) {
                        users = response.body().getUsers();
                    } else if (page > 1) {
                        users.addAll(response.body().getUsers());
                    }
                    callback.next(users);
                } else {
                    callback.next(users);
                }
            }

            @Override
            public void onFailure(Call<APIResponseUsers> call, Throwable t) {

            }
        });
    }


    //upload image (avatar/cover)
    public void uploadFile(Uri fileUri,String apiKey, final com.signatic.utils.Callback<String> callback) {
        // create upload service client
        UserService service =
                Configuration.getClientUpload().create(UserService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(mContext, fileUri);

        // create RequestBody instance from file
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("fileNames_0", file.getName(), requestFile);
        // finally, execute the request
        Call<APIResponseUpload> call = service.uploadAvatar(apiKey,body);
        call.enqueue(new Callback<APIResponseUpload>() {
            @Override
            public void onResponse(Call<APIResponseUpload> call,
                                   Response<APIResponseUpload> response) {
                APIResponseUpload responseUpload = response.body();
                if (responseUpload.getSuccess()==1) {
                    UserLogin.getUserLogin().setAvatarUrl(Configuration.UPLOAD_IMAGE_URL + responseUpload.getData()[0]);
                    callback.next(responseUpload.getData()[0]);
                } else {
                    Toast.makeText(mContext,"Have a error when upload image",Toast.LENGTH_SHORT).show();
                    callback.next("error");
                }
            }

            @Override
            public void onFailure(Call<APIResponseUpload> call, Throwable t) {
                Toast.makeText(mContext,"Have a error when upload image",Toast.LENGTH_SHORT).show();
                callback.next("error");
                Log.e("Upload error:", t.getMessage());
            }
        });
    }


    //update Vip
    public void updateVip(int vipDay, int userID,String apiKey, final com.signatic.utils.Callback<Boolean> callback){
        UserService userService = Configuration.getClient(apiKey).create(UserService.class);
        Call<APIResponse<User>> call = userService.updateVip(vipDay,userID);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if(!apiResponse.isError()){
                    Log.d("Update Vip ",apiResponse.getMessage());
                    callback.next(!apiResponse.isError());
                }else{
                    Log.d("Update Vip ",apiResponse.getMessage());
                    callback.next(!apiResponse.isError());
                }
            }
            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("Update Vip ","",t);
                callback.next(false);
            }
        });
    }

    //update Coins
    public void updateCoins(final int coins, final int coinsOld, int userID,String apiKey, final com.signatic.utils.Callback<Boolean> callback){
        UserService userService = Configuration.getClient(apiKey).create(UserService.class);
        Call<APIResponse<User>> call = userService.updateCoins(coins,userID);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if(!apiResponse.isError()){
                    Log.d("Update Coins ",apiResponse.getMessage());
                    UserLogin.getUserLogin().setEarnCoins(coins);
                    callback.next(!apiResponse.isError());
                }else{
                    Log.d("Update Coins ",apiResponse.getMessage());
                    UserLogin.getUserLogin().setEarnCoins(coinsOld);
                    callback.next(!apiResponse.isError());
                }
            }
            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                Log.d("Update Vip ","",t);
                UserLogin.getUserLogin().setEarnCoins(coinsOld);
                callback.next(false);
            }
        });
    }

    //delete user
    public void deleteUser(int userID, final com.signatic.utils.Callback<Boolean> callback){
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.deleteUser(userID);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if(apiResponse.isError()){
                    callback.next(false);
                }else{
                    callback.next(true);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                callback.next(false);
                Log.d("Delete user","",t);
            }
        });
    }

    //update user like me
    public void updateLikeUser(int user_like_id, int user_id, final com.signatic.utils.Callback<Boolean> callback){
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.updateLikeUser(user_like_id,user_id);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if(!apiResponse.isError()){
                    callback.next(true);
                }else{
                    callback.next(false);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                    callback.next(false);
            }
        });
    }


    //update user visitor
    public void updateVisitor(int visitor_id, int user_id, final com.signatic.utils.Callback<Boolean> callback){
        UserService userService = Configuration.getClient().create(UserService.class);
        Call<APIResponse<User>> call = userService.updateVisitor(visitor_id,user_id);
        call.enqueue(new Callback<APIResponse<User>>() {
            @Override
            public void onResponse(Call<APIResponse<User>> call, Response<APIResponse<User>> response) {
                APIResponse<User> apiResponse = response.body();
                if(!apiResponse.isError()){
                    callback.next(true);
                }else{
                    callback.next(false);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<User>> call, Throwable t) {
                callback.next(false);
            }
        });
    }

    //endregion
}
