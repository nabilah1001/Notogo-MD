package com.dicoding.picodiploma.notogo_app.network

import com.dicoding.picodiploma.notogo_app.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Login
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    // Signup
    @FormUrlEncoded
    @POST("register")
    fun createAccount(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<SignupResponse>

    // Profile
    @GET("profile")
    fun getProfile(
        @Header("token") token: String
    ): Call<ProfileResponse>

    // Favorite
    @GET("profile/favorite")
    fun getFavorite(
        @Header("token") token: String
    ) : Call<FavoriteResponse>

    // History
    @GET("profile/history")
    fun getHistory(
        @Header("token") token: String
    ) : Call<HistoryResponse>

    // Goals
    @GET("goals")
    fun getGoals(
        @Header("token") token: String
    ) : Call<GoalsResponse>
}
