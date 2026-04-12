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

        // Ambil data dari Intent (dikirim dari WelcomeActivity)
        val judul = intent.getStringExtra("judul_halaman") ?: "Cek Kesehatan"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Pantau kondisi kesehatanmu setiap hari"

        // Hubungkan dengan TextView di XML
        val tvJudul = findViewById<TextView>(R.id.tvJudulHalaman)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsiDariUtama)
        val tvInfo = findViewById<TextView>(R.id.tvInfoTambahan)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        // Set teks
        tvJudul.text = judul
        tvDeskripsi.text = deskripsi
        tvInfo.text = "✅ Cek Berat Badan\n✅ Cek Tekanan Darah\n✅ Catat Riwayat Penyakit\n✅ Konsultasi dengan Dokter"

        // Tombol kembali
        btnKembali.setOnClickListener {
            finish()
        }
    }
}