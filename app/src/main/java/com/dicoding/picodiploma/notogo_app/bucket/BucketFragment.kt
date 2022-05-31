package com.dicoding.picodiploma.notogo_app.bucket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.databinding.FragmentBucketBinding

class BucketFragment : Fragment() {

    private var _binding: FragmentBucketBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bucketViewModel =
            ViewModelProvider(this).get(BucketViewModel::class.java)

        _binding = FragmentBucketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBucket
        bucketViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}