package com.signatic.service.services;

import com.signatic.model.User;
import com.signatic.service.Response.APIResponse;
import com.signatic.service.Response.APIResponseInteredUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by root on 11/11/2016.
 */

public interface InterestUser {

    @FormUrlEncoded
    @POST("getInteredUsers")
    Call<APIResponseInteredUser> getInteredUsers(@Field("lat") double lat,
                                                 @Field("lng") double lng,
                                                 @Field("Api key") String Api_key,
                                                 @Field("page") int page,
                                                 @Field("user_id") int user_id,
                                                 @Field("who_can_see_me") int index_who_can_see,
                                                 @Field("age_range_can_see_me_from") int age_range_from,
                                                 @Field("age_range_can_see_me_to") int age_range_to,
                                                 @Field("gender") int gender);

}
