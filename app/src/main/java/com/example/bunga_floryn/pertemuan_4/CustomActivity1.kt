package com.example.bunga_floryn.pertemuan_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bunga_floryn.R

class CustomActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        // Ambil data dari Intent (dikirim dari WelcomeActivity)
        val judul = intent.getStringExtra("judul_halaman") ?: "Cek Kesehatan"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Pantau kondisi kesehatanmu setiap hari"

        // Hubungkan dengan TextView di XML
        val tvJudul = findViewById<TextView>(R.id.tvJudulHalaman)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiDariUtama)
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnKembali = findViewById<LinearLayout>(R.id.btnKembali)

        tvJudul.text = judul
        tvDeskripsi.text = deskripsi

        btnBack.setOnClickListener { finish() }
        btnKembali.setOnClickListener { finish() }
    }
}