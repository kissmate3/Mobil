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
import androidx.navigation.fragment.navArgs
import com.example.android.mycats.R
import com.example.android.mycats.database.WorkDatabase
import com.example.android.mycats.databinding.FragmentWorkBinding
import com.google.android.material.snackbar.Snackbar


class WorkFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

    val binding: FragmentWorkBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_work, container, false)

    val application = requireNotNull(this.activity).application

    // Create an instance of the ViewModel Factory.
    val dataSource = WorkDatabase.getInstance(application).workDao
    val viewModelFactory = WorkViewModelFactory(dataSource, application)

    // Get a reference to the ViewModel associated with this fragment.
    val workViewModel =
        ViewModelProvider(
            this, viewModelFactory).get(WorkViewModel::class.java)

    // To use the View Model with data binding, you have to explicitly
    // give the binding object a reference to it.
    binding.workViewModel = workViewModel

    // Specify the current activity as the lifecycle owner of the binding.
    // This is necessary so that the binding can observe LiveData updates.
    binding.setLifecycleOwner(this)

    // Add an Observer on the state variable for showing a Snackbar message
    // when the CLEAR button is pressed.
        workViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
        if (it == true) { // Observed state is true.
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.cleared_message),
                Snackbar.LENGTH_SHORT // How long to display the message.
            ).show()
            // Reset state to make sure the toast is only shown once, even if the device
            // has a configuration change.
            workViewModel.doneShowingSnackbar()
        }
    })

    // Add an Observer on the state variable for Navigating when STOP button is pressed.
        workViewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer { night ->
        night?.let {
            // We need to get the navController from this, because button is not ready, and it
            // just has to be a view. For some reason, this only matters if we hit stop again
            // after using the back button, not if we hit stop and choose a quality.
            // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
            // popping the stack to get the correct behavior if we press stop multiple times
            // followed by back.
            // Also: https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
            this.findNavController().navigate(
                WorkFragmentDirections
                    .actionSleepTrackerFragmentToSleepQualityFragment(night.workId))
            // Reset state to make sure we only navigate once, even if the device
            // has a configuration change.
            workViewModel.doneNavigating()
        }
    })

    val adapter = WorkAdapter()
    binding.sleepList.adapter = adapter
        workViewModel.nights.observe(viewLifecycleOwner, Observer {
        it?.let {
            adapter.submitList(it)
        }
    })
    return binding.root
}
}