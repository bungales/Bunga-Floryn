package com.example.bunga_floryn.pertemuan_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bunga_floryn.R
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    private lateinit var etAlasSegitiga: EditText
    private lateinit var etTinggiSegitiga: EditText
    private lateinit var etJariBola: EditText
    private lateinit var btnHitungSegitiga: Button
    private lateinit var btnHitungBola: Button
    private lateinit var tvHasilSegitiga: TextView
    private lateinit var tvHasilBola: TextView
    private lateinit var btnKembali: Button  // <==== TAMBAHKAN INI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        etAlasSegitiga = findViewById(R.id.et_alas_segitiga)
        etTinggiSegitiga = findViewById(R.id.et_tinggi_segitiga)
        etJariBola = findViewById(R.id.et_jari_bola)
        btnHitungSegitiga = findViewById(R.id.btn_hitung_segitiga)
        btnHitungBola = findViewById(R.id.btn_hitung_bola)
        tvHasilSegitiga = findViewById(R.id.tv_hasil_segitiga)
        tvHasilBola = findViewById(R.id.tv_hasil_bola)
        btnKembali = findViewById(R.id.btnKembali)  // <==== TAMBAHKAN INI
    }

    private fun setupClickListeners() {
        btnHitungSegitiga.setOnClickListener { hitungLuasSegitiga() }
        btnHitungBola.setOnClickListener { hitungVolumeBola() }

        // <==== TAMBAHKAN INI UNTUK TOMBOL KEMBALI
        btnKembali.setOnClickListener {
            finish()  // Menutup activity ini dan kembali ke halaman sebelumnya
        }
    }

    private fun hitungLuasSegitiga() {
        try {
            val alasStr = etAlasSegitiga.text.toString().trim()
            val tinggiStr = etTinggiSegitiga.text.toString().trim()

            if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua inputan!", Toast.LENGTH_SHORT).show()
                return
            }

            val alas = alasStr.toDouble()
            val tinggi = tinggiStr.toDouble()

            if (alas <= 0 || tinggi <= 0) {
                Toast.makeText(this, "Nilai harus lebih dari 0!", Toast.LENGTH_SHORT).show()
                return
            }

            val luas = 0.5 * alas * tinggi
            tvHasilSegitiga.text = String.format("Luas Segitiga: %.2f cm²", luas)
            tvHasilSegitiga.visibility = TextView.VISIBLE

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Input harus berupa angka!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hitungVolumeBola() {
        try {
            val jariStr = etJariBola.text.toString().trim()

            if (jariStr.isEmpty()) {
                Toast.makeText(this, "Mohon isi jari-jari bola!", Toast.LENGTH_SHORT).show()
                return
            }

            val r = jariStr.toDouble()

            if (r <= 0) {
                Toast.makeText(this, "Jari-jari harus lebih dari 0!", Toast.LENGTH_SHORT).show()
                return
            }

            val volume = (4.0 / 3.0) * PI * Math.pow(r, 3.0)
            tvHasilBola.text = String.format("Volume Bola: %.2f cm³", volume)
            tvHasilBola.visibility = TextView.VISIBLE

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Input harus berupa angka!", Toast.LENGTH_SHORT).show()
        }
    }
}