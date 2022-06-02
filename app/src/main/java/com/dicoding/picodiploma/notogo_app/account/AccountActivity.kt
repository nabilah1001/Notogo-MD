package com.dicoding.picodiploma.notogo_app.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.recommend.Favorite
import com.dicoding.picodiploma.notogo_app.bucket.History
import com.dicoding.picodiploma.notogo_app.MainActivity
import com.dicoding.picodiploma.notogo_app.authentification.Onboarding
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.favoriteButton.setOnClickListener {
            startActivity(Intent(this, Favorite::class.java))
        }

        binding.goalsButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.historyButton.setOnClickListener {
            startActivity(Intent(this, History::class.java))
        }

        binding.logoutButton.setOnClickListener {
            startActivity(Intent(this, Onboarding::class.java))
        }
    }
}