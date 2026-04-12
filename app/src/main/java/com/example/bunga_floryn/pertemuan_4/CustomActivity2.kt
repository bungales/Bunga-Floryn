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

        // Ambil data dari Intent (dikirim dari WelcomeActivity)
        val judul = intent.getStringExtra("judul_halaman") ?: "Tips Sehat"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Terapkan gaya hidup sehat setiap hari"

        // Hubungkan dengan TextView di XML
        val tvJudul = findViewById<TextView>(R.id.tvJudulHalaman)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiDariUtama)
        val tvInfo = findViewById<TextView>(R.id.tvInfoTambahan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Set teks
        tvJudul.text = judul
        tvDeskripsi.text = deskripsi
        tvInfo.text = "💧 Minum air 8 gelas per hari\n🏃 Olahraga 30 menit setiap hari\n😴 Tidur cukup 7-8 jam\n🥗 Makan makanan bergizi seimbang"

        // Tombol kembali
        btnKembali.setOnClickListener {
            finish()
        }
    }
}