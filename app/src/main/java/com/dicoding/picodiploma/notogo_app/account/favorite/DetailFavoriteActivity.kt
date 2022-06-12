package com.dicoding.picodiploma.notogo_app.account.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailFavoriteBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItem

class DetailFavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataFavorite: ResultItem? = intent.getParcelableExtra("EXTRA_FAVORITE")

        if (dataFavorite != null) {
            binding.tvLocation.text = dataFavorite.location
            binding.tvCategory.text = dataFavorite.category.toString()

            Glide.with(this)
                .load(dataFavorite.image)
                .into(binding.imagecategory)

            binding.tvLocation.text = dataFavorite.location
        }
    }
}