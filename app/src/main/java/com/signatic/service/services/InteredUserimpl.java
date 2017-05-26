package com.signatic.service.services;

import android.content.Context;
import android.util.Log;

import com.signatic.model.User;
import com.signatic.model.UserInterested;
import com.signatic.service.Configuration;
import com.signatic.service.Response.APIResponseInteredUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 11/11/2016.
 */

public class InteredUserimpl extends BaseService<InterestUser> {
    Context mContext;

    public InteredUserimpl(Context context){
        mContext=context;
    }
    public void getInteredUsers(User user,int page, final com.signatic.utils.Callback<ArrayList<UserInterested>> callback){
        InterestUser interestUser = Configuration.getClient(user.getApiKey()).create(InterestUser.class);
        Call<APIResponseInteredUser> call=interestUser.getInteredUsers(user.getLatitude(),user.getLongtitude(),user.getApiKey(),page,user.getID(),user.getWhoCanSeeMe(),user.getAgeRangeFrom(),user.getAgeRangeTo(),user.getGender());
        call.enqueue(new Callback<APIResponseInteredUser>() {
            @Override
            public void onResponse(Call<APIResponseInteredUser> call, Response<APIResponseInteredUser> response) {
                APIResponseInteredUser apiResponse=response.body();
                if(!apiResponse.isError()){
                    callback.next(apiResponse.getmUsers());
                }
            }

            @Override
            public void onFailure(Call<APIResponseInteredUser> call, Throwable t) {

            }
        });
    }
}
