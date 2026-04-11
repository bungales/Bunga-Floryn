package com.example.bunga_floryn.pertemuan_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bunga_floryn.R

class CustomActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        android.util.Log.e("onCreate", "CustomActivity1 dibuat pertama kali")

        val tvJudulHalaman = findViewById<TextView>(R.id.tvJudulHalaman)
        val tvDeskripsiDariUtama = findViewById<TextView>(R.id.tvDeskripsiDariUtama)
        val tvInfoTambahan = findViewById<TextView>(R.id.tvInfoTambahan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Ambil data dari WelcomeActivity
        val judul = intent.getStringExtra("judul_halaman") ?: "Custom Screen 1"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Desain Figma"

        tvJudulHalaman.text = judul
        tvDeskripsiDariUtama.text = deskripsi
        tvInfoTambahan.text = "Ini adalah halaman custom pertama.\nDesain sesuai Figma dengan gambar dan teks."

        btnKembali.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        android.util.Log.e("onStart", "CustomActivity1 terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.e("onDestroy", "CustomActivity1 dihapus dari stack")
    }
}