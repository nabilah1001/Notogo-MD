package com.dicoding.picodiploma.notogo_app.authentification

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.*
import com.dicoding.picodiploma.notogo_app.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.notogo_app.model.response.LoginResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityLoginBinding
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        //btn login
        binding.emailEditText.type = "email"
        binding.passwordEditText.type = "password"

        binding.loginButton.setOnClickListener {
            val inputEmail = binding.emailEditText.text.toString()
            val inputPassword = binding.passwordEditText.text.toString()

            login(inputEmail, inputPassword)
        }

        //btn signup
        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        setupView()
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

    //Function btn login
    private fun login(inputEmail: String, inputPassword: String) {
        showLoading(true)

        val client = ApiConfig.getApiService().login(inputEmail, inputPassword)
        client.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                showLoading(false)

                val responseBody = response.body()
                Log.d(TAG, "onResponse: $responseBody")

                if(response.isSuccessful && responseBody?.message == "Logged in!") {
                    val token = response.body()?.token.toString()
                    tokenViewModel.saveTokens(token)
                    Toast.makeText(this@LoginActivity, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Log.e(TAG, "onFailure1: ${response.message()}")
                    Toast.makeText(this@LoginActivity, getString(R.string.login_fail), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure2: ${t.message}")
                Toast.makeText(this@LoginActivity, getString(R.string.login_fail), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val emailTextView = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500)
        val emailEditTextLayout = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordTextView = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 500
        }.start()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "Login Activity"
    }

}