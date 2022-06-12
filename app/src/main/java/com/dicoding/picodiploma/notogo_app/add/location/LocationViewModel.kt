package com.dicoding.picodiploma.notogo_app.add.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.notogo_app.model.response.ItemLocation
import com.dicoding.picodiploma.notogo_app.model.response.LocationResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationViewModel : ViewModel() {

    val listLocations = MutableLiveData<ArrayList<ItemLocation>>()

    fun setSearchLocations(query: String){
        val client = ApiConfig.getApiService().getSearchLocations(query)
        client.enqueue(object : Callback<LocationResponse> {
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if (response.isSuccessful){
                    listLocations.postValue(response.body()?.result)
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Log.e("Failure", t.message!!)
            }
        })
    }

    fun getSearchLocations(): LiveData<ArrayList<ItemLocation>> {
        return listLocations
    }
}