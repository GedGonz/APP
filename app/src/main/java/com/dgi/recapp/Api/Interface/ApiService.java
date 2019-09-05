package com.dgi.recapp.Api.Interface;

import com.dgi.recapp.Api.Model.AccessToken;
import com.dgi.recapp.Api.Model.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("token")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);

    @POST("social_auth")
    @FormUrlEncoded
    Call<AccessToken> socialAuth(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("provider") String provider,
                                 @Field("provider_user_id") String providerUserId);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("api/Menu")
    Call<List<Menu>> getMenu();

    //@GET("posts")

}