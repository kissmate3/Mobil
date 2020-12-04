package com.example.android.mycats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.android.mycats.databinding.FragmentInfo3Binding

class Info3Fragment : Fragment() {
    private lateinit var binding: FragmentInfo3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info3, container, false)
        binding.FinishButton.setOnClickListener { view: View ->
            view.findNavController().navigate(Info3FragmentDirections.info3Totitlefrag())
        }
        binding.Back2Button.setOnClickListener { view: View ->
            view.findNavController().navigate(Info3FragmentDirections.inf3toinf2())
        }
        return binding.root
    }
}