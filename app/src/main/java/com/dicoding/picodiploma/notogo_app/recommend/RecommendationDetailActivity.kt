package com.dicoding.picodiploma.notogo_app.recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailTravelBinding
import com.dicoding.picodiploma.notogo_app.databinding.ActivityRecommendationDetailBinding

class RecommendationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}