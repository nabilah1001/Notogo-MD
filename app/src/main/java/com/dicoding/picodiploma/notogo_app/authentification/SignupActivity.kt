package com.dicoding.picodiploma.notogo_app.authentification

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.picodiploma.notogo_app.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameEditText.type = "name"
        binding.emailEditText.type = "email"
        binding.passwordEditText.type = "password"

        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

//            setupAction(name, email, password)
        }

        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

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

//    private fun setupAction(name: String, email: String, password: String) {
//        showLoading(true)
//
//        val client = ApiConfig.getApiService().createAccount(name, email, password)
//        client.enqueue(object: Callback<SignupResponse> {
//            override fun onResponse(
//                call: Call<SignupResponse>,
//                response: Response<SignupResponse>
//            ) {
//                showLoading(false)
//                val responseBody = response.body()
//                Log.d(ContentValues.TAG, "onResponse: $responseBody")
//                if(response.isSuccessful && responseBody?.message == "User created") {
//                    Toast.makeText(this@SignupActivity, getString(R.string.register_success), Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    Log.e(ContentValues.TAG, "onFailure1: ${response.message()}")
//                    Toast.makeText(this@SignupActivity, getString(R.string.register_fail), Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(ContentValues.TAG, "onFailure2: ${t.message}")
//                Toast.makeText(this@SignupActivity, getString(R.string.register_fail), Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val nameTextView = ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(500)
        val nameEditTextLayout = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val emailTextView = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500)
        val emailEditTextLayout = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordTextView = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)


        AnimatorSet().apply {
            playSequentially(
                title,
                nameTextView,
                nameEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 500
        }.start()
    }
}