package com.example.bunga_floryn.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunga_floryn.data.model.BeritaModel
import com.example.bunga_floryn.databinding.ItemBeritaBinding

class BeritaAdapter(private val items: List<BeritaModel>) :
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {
    inner class BeritaViewHolder(val binding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvJudulBerita.text = item.judul
        holder.binding.tvDeskripsiBerita.text = item.deskripsi
        holder.binding.tvKategori.text = item.kategori
        holder.binding.tvTanggal.text = item.tanggal
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(android.R.color.darker_gray)
            .into(holder.binding.imgBerita)
    }

    override fun getItemCount(): Int = items.size
}
