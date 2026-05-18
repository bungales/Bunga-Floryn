package com.example.bunga_floryn.Home.pertemuan_9

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.bunga_floryn.databinding.ItemWargaBinding

class WargaAdapter(
    context: Context,
    private val items: List<WargaModel>
) : ArrayAdapter<WargaModel>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemWargaBinding.inflate(LayoutInflater.from(context), parent, false)
        val d = items[position]

        val isLaki = d.gender == "L"
        val avatarColor  = if (isLaki) "#5C6BC0" else "#7E57C2"
        val badgeColor   = if (isLaki) "#1565C0" else "#AD1457"
        val jobColor     = "#FF8F00"

        // Avatar inisial
        binding.tvAvatar.text = d.nama.first().uppercase()
        binding.tvAvatar.background.setTint(Color.parseColor(avatarColor))

        binding.tvNama.text = d.nama
        binding.tvNik.text  = "NIK: ${d.nik}"

        // Badge gender
        binding.tvGenderBadge.text = if (isLaki) "♂ L" else "♀ P"
        binding.tvGenderBadge.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 20f
            setColor(Color.parseColor(badgeColor))
        }

        // Agama & Pekerjaan
        binding.tvAgama.text = "👑 ${d.agama}"
        binding.tvPekerjaan.text = d.pekerjaan
        binding.tvPekerjaan.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 16f
            setColor(Color.parseColor(jobColor))
        }

        // HP + Email
        binding.tvHpEmail.text = "📞 ${d.noHp}   ✉ ${d.email}"

        // Alamat
        binding.tvAlamat.text = "📍 ${d.alamat}"

        return binding.root
    }
}
