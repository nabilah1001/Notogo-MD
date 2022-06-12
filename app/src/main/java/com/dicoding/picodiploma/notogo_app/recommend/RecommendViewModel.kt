package com.dicoding.picodiploma.notogo_app.recommend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.model.response.GoalsResponse
import com.dicoding.picodiploma.notogo_app.model.response.RecommendationResponse
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemGoals
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemRecommendation
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel : ViewModel() {

    companion object {
        private const val TAG = "Recommend Fragment"
    }

    val recommendList = MutableLiveData<List<ResultItemRecommendation>>()

    fun getListRecommend(): MutableLiveData<List<ResultItemRecommendation>> {
        return recommendList
    }

    fun setListRecommend(token: String) {
        val client = ApiConfig.getApiService().getRecommend(token)
        client.enqueue(object : Callback<RecommendationResponse> {
            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                if (response.isSuccessful) {
                    recommendList.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

}