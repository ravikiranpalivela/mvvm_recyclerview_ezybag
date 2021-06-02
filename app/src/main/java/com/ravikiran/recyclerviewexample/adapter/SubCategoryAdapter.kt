package com.ravikiran.recyclerviewexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravikiran.recyclerviewexample.databinding.RecyclerHomeItemsBinding
import com.ravikiran.recyclerviewexample.model.SubCategory

class SubCategoryAdapter : RecyclerView.Adapter<SubCategoryMainholder>() {

    var products = mutableListOf<SubCategory>()
    lateinit var context: Context

    fun setMovieList(products: List<SubCategory>?, applicationContext: Context) {
        this.products = products!!.toMutableList()
        this.context = applicationContext
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryMainholder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RecyclerHomeItemsBinding.inflate(inflater, parent, false)
        return SubCategoryMainholder(binding)
    }


    private var onItemClickCallback: ((SubCategory) -> Unit?)? = null

    fun setOnClickCallback(callback: (SubCategory) -> Unit?) {
        onItemClickCallback = callback
    }

    override fun onBindViewHolder(holder: SubCategoryMainholder, position: Int) {
        val products = products[position]
        holder.binding.tHome.text = products.name



        Glide.with(holder.itemView.context).load("http://ezybag.in/controladmin/" + products.img).into(holder.binding.imgItem)

        holder.binding.root.setOnClickListener {
            onItemClickCallback?.let { callback ->
                callback(products)
            }
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class SubCategoryMainholder(val binding: RecyclerHomeItemsBinding) : RecyclerView.ViewHolder(binding.root)