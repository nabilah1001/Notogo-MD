package com.dicoding.picodiploma.notogo_app.bucket

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.notogo_app.TokenPreference
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.ViewModelFactory
import com.dicoding.picodiploma.notogo_app.databinding.FragmentBucketBinding

class BucketFragment() : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private var _binding: FragmentBucketBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBucketBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TokenViewModel
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(TokenPreference.getInstance(requireContext().dataStore)))[TokenViewModel::class.java]

        //set goals
        val bucketViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[BucketViewModel::class.java]
        tokenViewModel.getTokens().observe(viewLifecycleOwner) { token: String? ->
            if (token != null){
                bucketViewModel.setListGoals(token)

            }
        }

        //get goals
        bucketViewModel.getListGoals().observe(viewLifecycleOwner){
            if (it != null) {
                val adapter = ListBucketAdapter(it)
                binding.rvGoals.adapter = adapter
                val layoutManager = LinearLayoutManager(activity)
                binding.rvGoals.layoutManager = layoutManager
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}