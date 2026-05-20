package com.example.bunga_floryn.Home.pertemuan_10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunga_floryn.R

class DataAdapter(private val list: List<DataModel>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvProductName: TextView = view.findViewById(R.id.tvProductName)
        val tvProductPrice: TextView = view.findViewById(R.id.tvProductPrice)
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvProductName.text = item.title
        holder.tvProductPrice.text = item.subtitle
        
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .centerCrop()
            .into(holder.imgProduct)
    }

    override fun getItemCount(): Int = list.size
}