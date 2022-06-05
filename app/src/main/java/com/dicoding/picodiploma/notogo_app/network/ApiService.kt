package com.dicoding.picodiploma.notogo_app.network

import com.dicoding.picodiploma.notogo_app.model.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    // Login
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email :String,
        @Field("password") password :String
    ): Call<LoginResponse>
}
