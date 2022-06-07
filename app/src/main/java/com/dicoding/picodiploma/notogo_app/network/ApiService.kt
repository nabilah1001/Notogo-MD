package com.dicoding.picodiploma.notogo_app.network

import com.dicoding.picodiploma.notogo_app.model.response.LoginResponse
import com.dicoding.picodiploma.notogo_app.model.response.ProfileResponse
import com.dicoding.picodiploma.notogo_app.model.response.SignupResponse
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
        @Header("token") token: String,
    ): Call<ProfileResponse>

}
