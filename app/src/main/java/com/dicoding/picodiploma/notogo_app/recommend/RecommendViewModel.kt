package com.dicoding.picodiploma.notogo_app.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecommendViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Recommend Fragment"
    }
    val text: LiveData<String> = _text
}