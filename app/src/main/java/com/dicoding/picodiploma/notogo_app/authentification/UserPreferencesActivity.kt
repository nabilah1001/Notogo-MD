package com.dicoding.picodiploma.notogo_app.authentification

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.*
import com.dicoding.picodiploma.notogo_app.databinding.ActivityUserPreferencesBinding
import com.dicoding.picodiploma.notogo_app.model.response.PreferencesResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import kotlinx.android.synthetic.main.activity_user_preferences.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPreferencesActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityUserPreferencesBinding
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var mToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        tokenViewModel.getTokens().observe(this
        ) { token: String? ->
            if (token != null){
                mToken = token
            }
        }
//        binding.nameEditText.type = "name"
//        binding.emailEditText.type = "email"
//        binding.passwordEditText.type = "password"

        //btn signup
        val beach = binding.chipGroup.chipBeach
        val mountain = binding.chipGroup.chipMountain
        val lake = binding.chipGroup.chipLake
        val zoo = binding.chipGroup.chipZoo
        val river = binding.chipGroup.chipRiver
        val conservation = binding.chipGroup.chipConservation
        val waterPark = binding.chipGroup.chipWaterPark
        val waterfall = binding.chipGroup.chipWaterfall
        val artGallery = binding.chipGroup.chipArtGal
        val amusementPark = binding.chipGroup.chipAmusementPark
        val mall = binding.chipGroup.chipMall
        val historicalPlace = binding.chipGroup.chipMonument
        val religious = binding.chipGroup.chipReligiousPlace
        val outbond = binding.chipGroup.chipOutbound
        val photoHunting = binding.chipGroup.chipPhotoHunt
        val sightSeeing = binding.chipGroup.chipSightSeeing
        val shopping = binding.chipGroup.chipShopping
        binding.getStartedButton.setOnClickListener {

            Toast.makeText(this, "Isi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show()
//            val inputName = binding.nameEditText.text.toString()
//            val inputEmail = binding.emailEditText.text.toString()
//            val inputPassword = binding.passwordEditText.text.toString()
            val preferencesId = ArrayList<Int>(18)

            getStartedButton(mToken,preferencesId)
        }

        setupView()
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

    //Function btn signup
    private fun getStartedButton(token: String, preferencesId: ArrayList<Int>) {

        val client = ApiConfig.getApiService().getPreferences(token, preferencesId)
        client.enqueue(object: Callback<PreferencesResponse> {
            override fun onResponse(
                call: Call<PreferencesResponse>,
                response: Response<PreferencesResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Toast.makeText(this@UserPreferencesActivity, responseBody.message, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@UserPreferencesActivity, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PreferencesResponse>, t: Throwable) {
                Toast.makeText(this@UserPreferencesActivity, "Gagal instance Retrofit", Toast.LENGTH_SHORT).show()
            }

        })
    }

//        private fun setupChip() {
//            binding.apply {
//
//                if(chipBeach.isChecked){
//
//                }
//                chipMountain
//                chipLake
//                chipZoo
//
//
//                chipGroup.setOnCheckedChangeListener { chip, isChecked ->
//
//                }
//
//            }
//
//        }
}
