package com.example.android.mycats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.mycats.databinding.FragmentCatsBinding

class CatsFragment : Fragment() {
    private lateinit var binding: FragmentCatsBinding
    private lateinit var catViewModel: CatViewModel
    private lateinit var adapter: MyRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cats, container, false)
        val application = requireNotNull(this.activity).application
        val dao = CatDatabase.getInstance(application).CatDAO
        val repository = CatRepository(dao)
        val factory =  CatViewModelFactory(repository)
        catViewModel = ViewModelProvider(this,factory).get(CatViewModel::class.java)
        binding.myViewModel = catViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        catViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                //Toast.makeText(, it, Toast.LENGTH_LONG).show()
            }
        })
        return binding.root
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MyRecyclerViewAdapter { selectedItem: Cat -> listItemClicked(selectedItem) }
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        catViewModel.cats.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(cat: Cat){
        //Toast.makeText(this,"selected name is ${subscriber.name}",Toast.LENGTH_LONG).show()
        catViewModel.initUpdateAndDelete(cat)
    }
}