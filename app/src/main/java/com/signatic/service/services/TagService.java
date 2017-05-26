package com.signatic.service.services;

import com.signatic.service.Response.APIResponseTag;
import com.signatic.service.Response.APIResponseTags;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by nhutdu on 10/28/16.
 */
public interface TagService {

    @FormUrlEncoded
    @POST("searchTags")
    Call<APIResponseTags> searchTags(@Field("name") String name);

    @FormUrlEncoded
    @POST("createTag")
    Call<APIResponseTag> createTag(@Field("user_id") int userID, @Field("tag") String name);
}
