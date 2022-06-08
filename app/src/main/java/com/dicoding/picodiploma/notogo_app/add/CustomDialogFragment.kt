package com.dicoding.picodiploma.notogo_app.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {

    lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_custom_dialog, container, false)

//        binding.btnCancel.setOnClickListener {
//            val intent = Intent(this@CustomDialogFragment.requireContext(), AddActivity::class.java)
//            startActivity(intent)
//        }

        return rootView
    }

}