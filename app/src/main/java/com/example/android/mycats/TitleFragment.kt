package com.example.android.mycats

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.mycats.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        binding.catsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragment2ToCatsFragment())
        }
        binding.workButton.setOnClickListener { view: View ->
            val action= TitleFragmentDirections.actionTitleFragment2ToWorkFragment()
            view.findNavController().navigate(action)
        }
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.main_activity_title)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}