package com.dicoding.picodiploma.notogo_app.bucket

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.databinding.FragmentBucketBinding
import com.dicoding.picodiploma.notogo_app.model.response.GoalsResponse
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemGoals
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BucketViewModel: ViewModel() {

    val goalsList = MutableLiveData<List<ResultItemGoals>>()

    fun setListGoals(token: String) {
        val client = ApiConfig.getApiService().getGoals(token)

        client.enqueue(object : Callback<GoalsResponse> {
            override fun onResponse(
                call: Call<GoalsResponse>,
                response: Response<GoalsResponse>
            ) {
                if (response.isSuccessful) {
                    goalsList.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<GoalsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getListGoals(): MutableLiveData<List<ResultItemGoals>> {
        return goalsList
    }

    companion object {
        private const val TAG = "Bucket Fragment"
    }
}