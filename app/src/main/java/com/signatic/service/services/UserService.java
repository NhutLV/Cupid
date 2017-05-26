package com.signatic.service.services;

import com.signatic.model.User;
import com.signatic.service.Response.APIResponse;
import com.signatic.service.Response.APIResponseUpload;
import com.signatic.service.Response.APIResponseUsers;

import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by NhutDu on 06/10/2016.
 */
public interface UserService {
    @FormUrlEncoded
    @POST("registerFb")
    Call<APIResponse<User>> registerFb(@Field("first_name") String firstName,
                                       @Field("last_name") String lastName,
                                       @Field("email") String email,
                                       @Field("social_id") String socialID);


    @FormUrlEncoded
    @POST("register")
    Call<APIResponse<User>> register(@Field("first_name") String first_name,
                                     @Field("last_name") String last_name,
                                     @Field("email") String email,
                                     @Field("password") String password,
                                     @Field("lat") double lat ,
                                     @Field("lng") double lng,
                                     @Field("age") int age,
                                     @Field("avatar") String avatar,
                                     @Field("country") String country,
                                     @Field("gender") int gender);

    @FormUrlEncoded
    @POST("login")
    Call<APIResponse<User>> login(@Field("email") String email,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("updateProfile")
    Call<APIResponse<User>> updateProfile(@Field("email") String email,
                                          @Field("user_id") int id,
                                          @Field("first_name") String first_name,
                                          @Field("last_name") String last_name,
                                          @Field("gender") int gender,
                                          @Field("lat") double lat ,
                                          @Field("lng") double lng,
                                          @Field("age") int age,
                                          @Field("avatar") String avatar,
                                          @Field("photo") String photo,
                                          @Field("snap_username") String snap,
                                          @Field("kik_username") String kik,
                                          @Field("instagram_username") String instagram,
                                          @Field("oovoo_username") String oovoo,
                                          @Field("skype_username") String skype,
                                          @Field("interested_in") int  interested,
                                          @Field("i_am_here_to") int i_am_here_to,
                                          @Field("relationship") int relationship,
                                          @Field("drinking") int drinking,
                                          @Field("smoking") int smoking,
                                          @Field("living") int living,
                                          @Field("kids") int kids,
                                          @Field("education") int education,
                                          @Field("job") String job,
                                          @Field("company") String company,
                                          @Field("display_name") String displayName,
                                          @Field("who_can_see_me") int index_who_can_see,
                                          @Field("age_range_can_see_me_from") int age_range_from,
                                          @Field("age_range_can_see_me_to") int age_range_to,
                                          @Field("push_notification") int notification,
                                          @Field("earn_coins") int earn_coins,
                                          @Field("vip_upgrade") Date vip_upgrade);

    @FormUrlEncoded
    @POST("getVisitorUsers")
    Call<APIResponseUsers> getVisitorUsers(@Field("user_id") int id,
                                            @Field("page") int page);

    @FormUrlEncoded
    @POST("getUsersLikeMe")
    Call<APIResponseUsers> getUsersLikeMe(@Field("user_id") int id,
                                           @Field("page") int page);

    @FormUrlEncoded
    @POST("getNearbyUsers")
    Call<APIResponseUsers> getNearbyUsers(@Field("lat") double lat,
                                          @Field("lng") double lng,
                                          @Field("who_can_see_me") int who_can_see_me,
                                          @Field("age_range_can_see_me_from") int age_range_can_see_me_from,
                                          @Field("age_range_can_see_me_to") int age_range_can_see_me_to,
                                          @Field("gender") int gender,
                                          @Field("page") int page);

    @FormUrlEncoded
    @POST("getGlobalUsers")
    Call<APIResponseUsers> getGlobalUsers(@Field("lat") double lat,
                                          @Field("lng") double lng,
                                          @Field("who_can_see_me") int who_can_see_me,
                                          @Field("age_range_can_see_me_from") int age_range_can_see_me_from,
                                          @Field("age_range_can_see_me_to") int age_range_can_see_me_to,
                                          @Field("gender") int gender,
                                          @Field("page") int page);

    @Multipart
    @POST("uploadData.php")
    Call<APIResponseUpload> uploadAvatar(@Header("Authorization") String authorization, @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("updateVip")
    Call<APIResponse<User>> updateVip(@Field("vip_days")int vip_day,
                                      @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("updateCoins")
    Call<APIResponse<User>> updateCoins(@Field("earn_coins")int earn_coins,
                                        @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("updateVisitor")
    Call<APIResponse<User>> updateVisitor(@Field("visitor_id")int visitor_id,
                                          @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("updateLikeUser")
    Call<APIResponse<User>> updateLikeUser(@Field("user_like_id")int user_like_id,
                                           @Field("user_id") int user_id);

    @FormUrlEncoded
    @POST("deleteUser")
    Call<APIResponse<User>> deleteUser(@Field("user_id") int user_id);

}
