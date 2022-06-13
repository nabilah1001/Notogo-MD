package com.dicoding.picodiploma.notogo_app.authentification

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
import com.dicoding.picodiploma.notogo_app.databinding.ActivityUserPreferencesBinding
import com.dicoding.picodiploma.notogo_app.model.response.PreferencesResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import kotlinx.android.synthetic.main.activity_user_preferences.*
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
        binding.getStartedButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //btn signup
//        val preferencesId: Array<Int> = arrayOf(121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138)
//        val beach = chipGroup.chipBeach
//        val mountain = chipGroup.chipMountain
//        val lake = chipGroup.chipLake
//        val zoo = chipGroup.chipZoo
//        val river = chipGroup.chipRiver
//        val conservation = chipGroup.chipConservation
//        val waterPark = chipGroup.chipWaterPark
//        val waterfall = chipGroup.chipWaterfall
//        val artGallery = chipGroup.chipArtGal
//        val amusementPark = chipGroup.chipAmusementPark
//        val mall = chipGroup.chipMall
//        val historicalPlace = chipGroup.chipMonument
//        val religious = chipGroup.chipReligiousPlace
//        val outbond = chipGroup.chipOutbound
//        val culinary = chipGroup.chipCulinary
//        val photoHunting = chipGroup.chipPhotoHunt
//        val sightSeeing = chipGroup.chipSightSeeing
//        val shopping = chipGroup.chipShopping
//
//        getStartedButton.setOnClickListener (View.OnClickListener {
//            if (beach.isChecked) {
//                preferencesId[121]
//            }
//            if (mountain.isChecked) {
//                preferencesId[122]
//            }
//            if (lake.isChecked) {
//                preferencesId[123]
//            }
//            if (zoo.isChecked) {
//                preferencesId[124]
//            }
//            if (river.isChecked) {
//                preferencesId[125]
//            }
//            if (conservation.isChecked) {
//                preferencesId[126]
//            }
//            if (waterPark.isChecked) {
//                preferencesId[127]
//            }
//            if (waterfall.isChecked) {
//                preferencesId[128]
//            }
//            if (artGallery.isChecked) {
//                preferencesId[129]
//            }
//            if (amusementPark.isChecked) {
//                preferencesId[130]
//            }
//            if (mall.isChecked) {
//                preferencesId[131]
//            }
//            if (historicalPlace.isChecked) {
//                preferencesId[132]
//            }
//            if (religious.isChecked) {
//                preferencesId[133]
//            }
//            if (outbond.isChecked) {
//                preferencesId[134]
//            }
//            if (culinary.isChecked) {
//                preferencesId[135]
//            }
//            if (photoHunting.isChecked) {
//                preferencesId[136]
//            }
//            if (sightSeeing.isChecked) {
//                preferencesId[137]
//            }
//            if (shopping.isChecked) {
//                preferencesId[138]
//            }
//                startActivity(Intent(this, MainActivity::class.java))
////            chipGroup.setOnCheckedChangeListener { group, checkedId ->
////                getStartedButton(mToken,preferencesId)
////            }
//        })

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

    //Function btn user preferences
//    private fun getStartedButton(token: String, preferencesId: Array<Int>) {
//
//        val client = ApiConfig.getApiService().getPreferences(token, preferencesId)
//        client.enqueue(object: Callback<PreferencesResponse> {
//            override fun onResponse(
//                call: Call<PreferencesResponse>,
//                response: Response<PreferencesResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val intent = Intent(this@UserPreferencesActivity,MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    showToast(response.message())
//                }
//            }
//            override fun onFailure(call: Call<PreferencesResponse>, t: Throwable) {
//                Log.e("FAILURE", "onFailure: ${t.message.toString()}")
//            }
//
//        })
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
