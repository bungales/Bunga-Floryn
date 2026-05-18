package com.example.bunga_floryn.Settings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.bunga_floryn.databinding.ItemSettingsBinding

class SettingsAdapter(
    context: Context,
    private val items: List<SettingsModel>
) : ArrayAdapter<SettingsModel>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSettingsBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = items[position]

        binding.tvSettingsTitle.text = data.title
        binding.tvSettingsDesc.text = data.description
        binding.ivSettingsIcon.setImageResource(data.iconResId)

        return binding.root
    }
}
