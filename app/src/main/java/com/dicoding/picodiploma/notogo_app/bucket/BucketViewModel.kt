package com.dicoding.picodiploma.notogo_app.bucket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BucketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Bucket List Fragment"
    }
    val text: LiveData<String> = _text
}