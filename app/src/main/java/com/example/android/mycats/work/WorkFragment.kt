package com.example.android.mycats.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.mycats.MainActivity
import com.example.android.mycats.R
import com.example.android.mycats.database.WorkDatabase
import com.example.android.mycats.databinding.FragmentWorkBinding
import com.google.android.material.snackbar.Snackbar


class WorkFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

    val binding: FragmentWorkBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_work, container, false)

    val application = requireNotNull(this.activity).application


    val dataSource = WorkDatabase.getInstance(application).workDao
    val viewModelFactory = WorkViewModelFactory(dataSource, application)


    val workViewModel =
        ViewModelProvider(
            this, viewModelFactory).get(WorkViewModel::class.java)


    binding.workViewModel = workViewModel

        val adapter = WorkAdapter(WorkListener { workId ->
            workViewModel.onWorkClicked(workId!!)
        })
        binding.workList.adapter = adapter

        workViewModel.works.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        workViewModel.navigateToWorkDetail.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                this.findNavController().navigate(WorkFragmentDirections.workToDetails(night))
                workViewModel.onWorkDetailNavigated()
            }
        })

    binding.setLifecycleOwner(this)



        workViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
        if (it == true) { // Observed state is true.
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.cleared_message),
                Snackbar.LENGTH_SHORT // How long to display the message.
            ).show()

            workViewModel.doneShowingSnackbar()
        }
    })

        workViewModel.navigateToWorkType.observe(viewLifecycleOwner, Observer { night ->
        night?.let {

            this.findNavController().navigate(
                WorkFragmentDirections
                    .actionSleepTrackerFragmentToSleepQualityFragment(night.workId))

            workViewModel.doneNavigating()
        }
    })

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (position) {
                0 -> 3
                else -> 1
            }

        }
        binding.workList.layoutManager = manager
        (activity as MainActivity).supportActionBar?.title = getString(R.string.main_activity_work)
    return binding.root
}
}