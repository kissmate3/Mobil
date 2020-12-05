package com.example.android.mycats.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.mycats.R
import com.example.android.mycats.databinding.FragmentInfo2Binding

class Info2Fragment : Fragment() {
    private lateinit var binding: FragmentInfo2Binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info2, container, false)
        binding.Next2button.setOnClickListener { view: View ->
            view.findNavController().navigate(Info2FragmentDirections.info2Toinfo3())
        }
        binding.Backbutton.setOnClickListener { view: View ->
            view.findNavController().navigate(Info2FragmentDirections.inf2Toinf())
        }
        return binding.root
    }
}