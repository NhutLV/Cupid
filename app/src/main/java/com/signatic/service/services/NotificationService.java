package com.signatic.service.services;

import com.signatic.model.User;
import com.signatic.service.Response.APIResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by root on 09/11/2016.
 */

public interface NotificationService {
    @FormUrlEncoded
    @POST
    Call<APIResponse<User>> sendNotification(@Field("first_name") String firstName,
                                       @Field("last_name") String lastName);
}
