package com.example.android.mycats.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.mycats.MainActivity
import com.example.android.mycats.R
import com.example.android.mycats.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
         binding.NextButton.setOnClickListener { view: View ->
            view.findNavController().navigate(InfoFragmentDirections.infoToinfo2())
        }
        (activity as MainActivity).supportActionBar?.title = getString(R.string.main_activity_info)
        return binding.root
    }
}