package com.example.android.mycats


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mycats.databinding.ListItemBinding

class MyRecyclerViewAdapter(private val clickListener:(Cat)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val catsList = ArrayList<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding : ListItemBinding =
          DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
      return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return catsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(catsList[position],clickListener)
    }

    fun setList(cat: List<Cat>) {
        catsList.clear()
        catsList.addAll(cat)

    }

}

class MyViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(cat: Cat,clickListener:(Cat)->Unit){
          binding.nameTextView.text = cat.name
          binding.emailTextView.text = cat.species
          binding.ageTextView.text = cat.age
          binding.listItemLayout.setOnClickListener{
             clickListener(cat)
          }
    }
}