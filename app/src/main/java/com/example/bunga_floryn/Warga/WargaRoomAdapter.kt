package com.example.bunga_floryn.Warga

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunga_floryn.data.entity.WargaEntity
import com.example.bunga_floryn.databinding.ItemWargaRoomBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WargaRoomAdapter(
    private val list: List<WargaEntity>,
    private val fragment: WargaFragment
) : RecyclerView.Adapter<WargaRoomAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemWargaRoomBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWargaRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val warga = list[position]
        holder.binding.tvNama.text = warga.nama
        holder.binding.tvNik.text = "NIK: ${warga.nik}"
        holder.binding.tvAlamat.text = warga.alamat
        holder.binding.tvNoTelp.text = warga.noTelp

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Data Warga")
                .setMessage("Hapus data \"${warga.nama}\" dari daftar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    fragment.deleteWarga(warga)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun getItemCount(): Int = list.size
}
