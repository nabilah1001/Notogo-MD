package com.dicoding.picodiploma.notogo_app.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAddBinding
import com.dicoding.picodiploma.notogo_app.databinding.ActivitySearchLocationBinding

class SearchLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}