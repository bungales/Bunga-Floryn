package com.example.bunga_floryn.pertemuan_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bunga_floryn.R

class CustomActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom2)

        android.util.Log.e("onCreate", "CustomActivity2 dibuat pertama kali")

        val tvJudulHalaman = findViewById<TextView>(R.id.tvJudulHalaman)
        val tvDeskripsiDariUtama = findViewById<TextView>(R.id.tvDeskripsiDariUtama)
        val tvInfoTambahan = findViewById<TextView>(R.id.tvInfoTambahan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val judul = intent.getStringExtra("judul_halaman") ?: "Custom Screen 2"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Desain Figma"

        tvJudulHalaman.text = judul
        tvDeskripsiDariUtama.text = deskripsi
        tvInfoTambahan.text = "Ini adalah halaman custom kedua.\nDesain berbeda dengan warna dan ilustrasi yang berbeda."

        btnKembali.setOnClickListener {
            finish()
        }
    }
}