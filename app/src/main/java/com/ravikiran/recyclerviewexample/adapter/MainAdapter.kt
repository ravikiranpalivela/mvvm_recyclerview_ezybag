package com.ravikiran.recyclerviewexample.adapter

import android.R
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravikiran.recyclerviewexample.App
import com.ravikiran.recyclerviewexample.databinding.ItemProductsBinding
import com.ravikiran.recyclerviewexample.model.LatestProducts
import org.json.JSONException
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var products = mutableListOf<LatestProducts>()
    lateinit var context: Context

    fun setMovieList(products: List<LatestProducts>, applicationContext: Context) {
        this.products = products.toMutableList()
        this.context = applicationContext
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemProductsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val products = products[position]
        holder.binding.tvTitle.text = products.prod_name


        holder.binding.tvDesc.text = products.description
        holder.binding.tvId.text = products.prod_id

        var spinnerArrayList: MutableList<String> = ArrayList()

        spinnerArrayList = products.qty as MutableList<String>

        val app = App()

        var dataAdapter =
            ArrayAdapter(
                context,
                R.layout.simple_spinner_item, spinnerArrayList
            )

        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        holder.binding.spinnerItems.adapter = dataAdapter

        holder.binding.spinnerItems.setSelection(0)
//
        holder.binding.spinnerItems.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, itemPosition: Int, id: Long) {
                try {
                    holder.binding.tvDisPrice.text = "MRP." + products.mrpprice!![itemPosition]
                    holder.binding.tvPrice.text = "₹" + products.price!![itemPosition]
                    holder.binding.tvDisPrice.paintFlags = holder.binding.tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//                    if (products.discount!![itemPosition] == "0.00") {
//                        holder.binding.tvDiscountPer.visibility = View.GONE
//                        holder.binding.tvDisPrice.visibility = View.GONE
//                    } else {
                        holder.binding.tvDiscountPer.visibility = View.VISIBLE
                        holder.binding.tvDisPrice.visibility = View.VISIBLE
//                        holder.binding.tvDiscountPer.text = products.discount!![itemPosition].toString() + "%"
//                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
//        holder.binding.addItemView.setMaxQuantity("Max: " + list.get(position).getMaxquantity())
//
//        if (list.get(position).getQuantity().equals("0")) {
//            holder.binding.addItemView.setVisibility(View.GONE)
//            holder.binding.btnAdd.setVisibility(View.VISIBLE)
//        }
//
//        val imageUrl: String = "http://ezybag.in/controladmin/" + list.get(position).getImage()
//
//        if (imageUrl.length <= 5) {
//            holder.binding.imgItem.setImageResource(R.drawable.menuitem_background)
//        } else {
//            Picasso.with(MyApp.getContext())
//                .load(imageUrl).fit().into(holder.binding.imgItem)
//        }
//
//        holder.binding.btnAdd.setOnClickListener(View.OnClickListener {
//            toggleAddView(false, itemHolder, position)
//            com.parikram.EzyBag.adapter.ProductItemAdapter.mListener.onAddButtonClick(position, holder.binding.spinnerItems.getSelectedItemPosition())
//        }
//        )
//
//        holder.binding.ivDelete.setOnClickListener(View.OnClickListener {
//            toggleAddView(true, itemHolder, position)
//            com.parikram.EzyBag.adapter.ProductItemAdapter.mListener.onProductDelete(position, holder.binding.spinner.getSelectedItemPosition())
//        }
//        )
//
//        holder.binding.imgItem.setOnClickListener(
//            View.OnClickListener { com.parikram.EzyBag.adapter.ProductItemAdapter.mListener.onProductView(position) }
//        )
//
//
//        holder.binding.addItemView.setQuantityChangedListener(object : OnQuantityChanged() {
//            fun setAddButton(isVisible: Boolean, qty: Int) {
//                var qty = qty
//                if (qty < products.getMaxquantity().toInt()) {
//                    com.parikram.EzyBag.adapter.ProductItemAdapter.mListener.onQuantityChange(position, qty.toString(), holder.binding.spinner.getSelectedItemPosition())
//                    if (isVisible) {
//                        toggleAddView(isVisible, itemHolder, position)
//                    } else {
//                        toggleAddView(isVisible, itemHolder, position)
//                    }
//                } else qty--
//            }
//        })


        Glide.with(holder.itemView.context).load("http://ezybag.in/controladmin/" + products.image).into(holder.binding.imgItem)

    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class MainViewHolder(val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root)