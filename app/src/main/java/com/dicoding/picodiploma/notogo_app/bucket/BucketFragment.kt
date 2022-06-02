package com.dicoding.picodiploma.notogo_app.bucket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.add.AddActivity
import com.dicoding.picodiploma.notogo_app.databinding.FragmentAddBinding
import com.dicoding.picodiploma.notogo_app.databinding.FragmentBucketBinding

class BucketFragment : Fragment() {

    private var _binding: FragmentBucketBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentBucketBinding.inflate(layoutInflater)

        bind.testGoal.setOnClickListener{
            val intent = Intent(this@BucketFragment.requireContext(), DetailGoalActivity::class.java)
            startActivity(intent)
        }
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}