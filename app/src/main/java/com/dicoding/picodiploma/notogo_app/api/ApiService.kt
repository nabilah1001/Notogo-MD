package com.dicoding.picodiploma.notogo_app.api

import com.dicoding.picodiploma.notogo_app.model.LoginResponse
import com.dicoding.picodiploma.notogo_app.model.SignupResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun createAccount(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<SignupResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>
}