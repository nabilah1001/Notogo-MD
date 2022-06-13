package com.dicoding.picodiploma.notogo_app.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.notogo_app.add.AddActivity
import com.dicoding.picodiploma.notogo_app.authentification.LoginActivity
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailTravelBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItem
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemRecommendation

class DetailTravel : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTravelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Detail Travel"

        val dataRecommend: ResultItemRecommendation? = intent.getParcelableExtra("EXTRA_RECOMMEND")

        if (dataRecommend != null) {
            binding.tvLocation.text = dataRecommend.location
            binding.tvCategory.text = dataRecommend.category.toString()

            Glide.with(this)
                .load(dataRecommend.image)
                .into(binding.imagecategory)

            binding.tvLocation.text = dataRecommend.location
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}