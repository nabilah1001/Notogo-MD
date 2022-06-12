package com.dicoding.picodiploma.notogo_app.account.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.model.response.ProfileResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel : ViewModel() {

    companion object {
        private const val TAG = "Account Activity"
    }
    val profile = MutableLiveData<ProfileResponse>()

    fun setProfile(token: String) {
        val client = ApiConfig.getApiService().getProfile(token)
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    profile.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getProfile(): LiveData<ProfileResponse> {
        return profile
    }
}