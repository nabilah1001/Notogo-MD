package com.dicoding.picodiploma.notogo_app.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.databinding.FragmentBucketBinding
import com.dicoding.picodiploma.notogo_app.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_recommend, container, false)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}