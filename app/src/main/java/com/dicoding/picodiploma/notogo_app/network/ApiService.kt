package com.dicoding.picodiploma.notogo_app.network

import com.dicoding.picodiploma.notogo_app.model.response.*
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
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
    @POST("goals/add")
    fun addGoal(
        @Header("token") token:String,
        @Body body: RequestBody
    ): Call<AddGoalResponse>

    // Upload Image
    @Multipart
    @POST("/goals/add/{goals_id}")
    fun uploadImg(
        @Part("goals_id") file: String
    ): Call<UploadImageResponse>

//    // Upload Image
//    @Multipart
//    @POST("/goals/add/{goals_id}")
//    fun uploadImg(
//        @Part file: MultipartBody.Part
//    ): Call<UploadImageResponse>

    // Recommendation
    @GET("recommendations")
    fun getRecommend(
        @Header("token") token: String
    ) : Call<RecommendationResponse>

    // User Preferences
    @FormUrlEncoded
    @POST("profile/preferences")
    fun getPreferences(
        @Header("token") token: String,
        @Field("preference_ids") preference_ids: Array<Int>
    ) : Call<PreferencesResponse>

    // Like Location
    @FormUrlEncoded
    @POST("recommendations/like")
    fun getLike(
        @Header("token") token: String,
        @Field("location_id") location_id: Int
    ) : Call<LikeResponse>
}
