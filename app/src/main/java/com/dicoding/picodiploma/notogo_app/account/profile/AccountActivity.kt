package com.dicoding.picodiploma.notogo_app.account.profile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.notogo_app.account.favorite.FavoriteActivity
import com.dicoding.picodiploma.notogo_app.account.history.HistoryActivity
import com.dicoding.picodiploma.notogo_app.MainActivity
import com.dicoding.picodiploma.notogo_app.TokenPreference
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.ViewModelFactory
import com.dicoding.picodiploma.notogo_app.authentification.Onboarding
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAccountBinding
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity() : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityAccountBinding
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        //set profile
        val accountViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[AccountViewModel::class.java]

        tokenViewModel.getTokens().observe(this) { token: String? ->
            if (token != null){
                accountViewModel.setProfile(token)
            }
        }

        //get profile
        accountViewModel.getProfile().observe(this) {
            if (it != null) {
                binding.apply {
                    tv_name.text = it.result?.name
                    tv_email.text = it.result?.email

                    Glide.with(this@AccountActivity)
                        .load(it.result?.photo)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .circleCrop()
                        .into(iv_profile)

                    tv_favorite.text = it.result?.favoriteCount.toString()
                    tv_history.text = it.result?.historyCount.toString()
                    tv_goals.text = it.result?.goalCount.toString()
                }
            }
        }

        //btn
        binding.favoriteButton.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        binding.goalsButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.logoutButton.setOnClickListener {
            tokenViewModel.removeTokens()
            val intent = Intent(this, Onboarding::class.java)
            startActivity(intent)
            finish()
        }
    }
}
