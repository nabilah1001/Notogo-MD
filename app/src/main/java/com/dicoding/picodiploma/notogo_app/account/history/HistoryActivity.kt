package com.dicoding.picodiploma.notogo_app.account.history

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
import com.dicoding.picodiploma.notogo_app.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TokenViewModel
        val pref = TokenPreference.getInstance(dataStore)
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(pref))[TokenViewModel::class.java]

        //set history
        val historyViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[HistoryViewModel::class.java]
        tokenViewModel.getTokens().observe(this) { token: String? ->
            if (token != null){
                historyViewModel.setListHistory(token)

            }
        }

        //get history
        historyViewModel.getListHistory().observe(this){
            if (it != null) {
                val adapter = ListHistoryAdapter(it)
                binding.rvHistory.adapter = adapter
                val layoutManager = LinearLayoutManager(this)
                binding.rvHistory.layoutManager = layoutManager
            }
        }
    }
}