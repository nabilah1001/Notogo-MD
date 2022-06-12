package com.dicoding.picodiploma.notogo_app.recommend

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.picodiploma.notogo_app.TokenPreference
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.ViewModelFactory
import com.dicoding.picodiploma.notogo_app.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenViewModel: TokenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecommendBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TokenViewModel
        tokenViewModel = ViewModelProvider(this, ViewModelFactory(TokenPreference.getInstance(requireContext().dataStore)))[TokenViewModel::class.java]

        //set goals
        val bucketViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[RecommendViewModel::class.java]
        tokenViewModel.getTokens().observe(viewLifecycleOwner) { token: String? ->
            if (token != null){
                bucketViewModel.setListRecommend(token)

            }
        }

        //get goals
        bucketViewModel.getListRecommend().observe(viewLifecycleOwner){
            if (it != null) {
                val adapter = ListRecommendationAdapter(it)
                binding.rvRecommend.adapter = adapter
                val layoutManager = GridLayoutManager(activity,2)
                binding.rvRecommend.layoutManager = layoutManager
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}