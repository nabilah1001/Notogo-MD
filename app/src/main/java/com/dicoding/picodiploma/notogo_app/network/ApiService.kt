package com.dicoding.picodiploma.notogo_app.network

import com.dicoding.picodiploma.notogo_app.model.response.*
import com.google.gson.JsonObject
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

    // Search Location
    @GET("location/search")
    fun getSearchLocations(
        @Query("search") query: String
    ): Call<LocationResponse>

    // Add Goal
    @FormUrlEncoded
    @POST("goals/add")
    fun addGoalUser(
        @Header("token") token: String,
        @Field("title") title: String,
        @Field("location_id") location_id: Int,
        @Field("location_name") location_name: String,
        @Field("budget") budget: Int,
        @Field("date") date: String,
        @Field("note") note: String
    ) : Call<AddGoalResponse>

//    @POST("goals/add")
//        AddResponse postJson(@Body Goal body)

    // Recommendation
    @GET("recommendations")
    fun getRecommend(
        @Header("token") token: String
    ) : Call<RecommendationResponse>

    // UserPreferences
    @FormUrlEncoded
    @PUT("profile/preferences")
    fun putPreferences(
        @Header("token") token: String,
        @Field("preference_ids") preference_ids: ArrayList<Int>
    ) : Call<PreferencesResponse>

    // LikeLocation
    @FormUrlEncoded
    @PUT("recommendations/like")
    fun putLike(
        @Header("token") token: String,
        @Path("id") id: Int
    ) : Call<LikeResponse>
}
