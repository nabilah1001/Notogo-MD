package com.dicoding.picodiploma.notogo_app.account.favorite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.model.response.FavoriteResponse
import com.dicoding.picodiploma.notogo_app.model.response.ResultItem
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(): ViewModel() {

    companion object {
        private const val TAG = "Favorite Activity"
    }

    val favoriteList = MutableLiveData<List<ResultItem>>()

    fun setListFavorite(token: String) {
        val client = ApiConfig.getApiService().getFavorite(token)
        client.enqueue(object : Callback<FavoriteResponse> {
            override fun onResponse(
                call: Call<FavoriteResponse>,
                response: Response<FavoriteResponse>
            ) {
                if (response.isSuccessful) {
                    favoriteList.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<FavoriteResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getListFavorite(): MutableLiveData<List<ResultItem>> {
        return favoriteList
    }
}