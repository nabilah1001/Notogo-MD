package com.dicoding.picodiploma.notogo_app.add

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.picodiploma.notogo_app.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View? {

        val bind = FragmentAddBinding.inflate(layoutInflater)

        bind.toTravel.setOnClickListener{
            val intent = Intent(this@AddFragment.requireContext(), AddActivity::class.java)
            startActivity(intent)
        }
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}