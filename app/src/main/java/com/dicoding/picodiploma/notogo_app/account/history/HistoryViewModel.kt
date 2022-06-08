package com.dicoding.picodiploma.notogo_app.account.history

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.model.response.HistoryResponse
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemHistory
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel(): ViewModel() {

    companion object {
        private const val TAG = "History Activity"
    }

    val historyList = MutableLiveData<List<ResultItemHistory>>()

    fun setListHistory(token: String) {
        val client = ApiConfig.getApiService().getHistory(token)
        client.enqueue(object : Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                if (response.isSuccessful) {
                    historyList.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getListHistory(): MutableLiveData<List<ResultItemHistory>> {
        return historyList
    }
}