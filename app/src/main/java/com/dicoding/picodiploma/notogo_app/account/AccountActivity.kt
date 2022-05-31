package com.dicoding.picodiploma.notogo_app.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.Favorite
import com.dicoding.picodiploma.notogo_app.History
import com.dicoding.picodiploma.notogo_app.MainActivity
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.authentification.Onboarding
import com.dicoding.picodiploma.notogo_app.authentification.SignupActivity
import com.dicoding.picodiploma.notogo_app.authentification.UserPreferencesActivity
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAccountBinding
import com.dicoding.picodiploma.notogo_app.databinding.ActivityLoginBinding

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