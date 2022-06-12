package com.dicoding.picodiploma.notogo_app.account.favorite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.notogo_app.TokenPreference
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.ViewModelFactory
import com.dicoding.picodiploma.notogo_app.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Favorite"

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        //set favorite
        val favoriteViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FavoriteViewModel::class.java]
        tokenViewModel.getTokens().observe(this) { token: String? ->
            if (token != null){
                favoriteViewModel.setListFavorite(token)

            }
        }

        //get favorite
        favoriteViewModel.getListFavorite().observe(this){
            if (it != null) {
                val adapter = ListFavoriteAdapter(it)
                binding.rvFavorite.adapter = adapter
                val layoutManager = LinearLayoutManager(this)
                binding.rvFavorite.layoutManager = layoutManager
            }
        }
    }
}