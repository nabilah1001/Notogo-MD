package com.dicoding.picodiploma.notogo_app.authentification

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.MainActivity
import com.dicoding.picodiploma.notogo_app.TokenPreference
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.ViewModelFactory
import com.dicoding.picodiploma.notogo_app.databinding.ActivityOnboardingBinding

class Onboarding : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]
        tokenViewModel.getTokens().observe(this
        ) { token: String? ->
            if (token != null){
                val intent = Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("tokenExtra",token)
                startActivity(intent)
                finish()
            }
        }

        setupView()
        setupAction()
        playAnimation()
    }

    //Fullscreen
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    //btn
    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.descTextView, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(login, signup)
        }

        AnimatorSet().apply {
            playSequentially(title, desc, together)
            start()
        }
    }
}
