package com.example.bunga_floryn.Home.pertemuan_4

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_floryn.databinding.ActivityCustom1Binding
import kotlin.math.pow

class CustomActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Cek Kesehatan Warga"
            subtitle = "Pemeriksaan Mandiri"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.btnCekKesehatan.setOnClickListener {
            hitungKesehatan()
        }
    }

    private fun hitungKesehatan() {
        val nama = binding.etNama.text?.toString()?.trim()
        val usiaStr = binding.etUsia.text?.toString()?.trim()
        val beratStr = binding.etBerat.text?.toString()?.trim()
        val tinggiStr = binding.etTinggi.text?.toString()?.trim()
        val sistolikStr = binding.etSistolik.text?.toString()?.trim()
        val diastolikStr = binding.etDiastolik.text?.toString()?.trim()

        if (nama.isNullOrEmpty() || usiaStr.isNullOrEmpty() ||
            beratStr.isNullOrEmpty() || tinggiStr.isNullOrEmpty() ||
            sistolikStr.isNullOrEmpty() || diastolikStr.isNullOrEmpty()
        ) {
            Toast.makeText(this, "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show()
            return
        }

        val berat = beratStr.toDoubleOrNull()
        val tinggi = tinggiStr.toDoubleOrNull()
        val sistolik = sistolikStr.toIntOrNull()
        val diastolik = diastolikStr.toIntOrNull()

        if (berat == null || tinggi == null || sistolik == null || diastolik == null || tinggi == 0.0) {
            Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
            return
        }

        // Hitung BMI
        val tinggiMeter = tinggi / 100.0
        val bmi = berat / tinggiMeter.pow(2)
        val bmiFormatted = String.format("%.1f", bmi)

        val (statusBMI, warnaBMI) = when {
            bmi < 18.5 -> Pair("Kurus", "#F57F17")
            bmi < 25.0 -> Pair("Normal ✓", "#2E7D32")
            bmi < 30.0 -> Pair("Gemuk", "#E65100")
            else -> Pair("Obesitas", "#C62828")
        }

        // Status tekanan darah
        val (statusTD, warnaTD) = when {
            sistolik < 90 || diastolik < 60 -> Pair("Hipotensi", "#F57F17")
            sistolik <= 120 && diastolik <= 80 -> Pair("Normal ✓", "#2E7D32")
            sistolik <= 130 && diastolik <= 80 -> Pair("Tinggi Ringan", "#E65100")
            sistolik <= 140 || diastolik <= 90 -> Pair("Hipertensi Tk.1", "#E65100")
            else -> Pair("Hipertensi Tk.2", "#C62828")
        }

        // Saran
        val saranList = mutableListOf<String>()
        when {
            bmi < 18.5 -> saranList.add("Tingkatkan asupan kalori dan protein. Konsumsi makanan bergizi seimbang.")
            bmi >= 25.0 -> saranList.add("Kurangi konsumsi lemak dan gula. Rutin olahraga minimal 30 menit/hari.")
            else -> saranList.add("Pertahankan berat badan ideal dengan pola makan sehat.")
        }
        when {
            sistolik > 130 || diastolik > 80 -> saranList.add("Kurangi konsumsi garam, hindari stress, rutin cek tekanan darah.")
            sistolik < 90 -> saranList.add("Perbanyak minum air putih, hindari berdiri terlalu cepat.")
            else -> saranList.add("Tekanan darah baik! Pertahankan dengan olahraga dan istirahat cukup.")
        }
        saranList.add("Minum air putih minimal 8 gelas per hari dan tidur 7–8 jam.")

        // Tampilkan hasil
        binding.tvBMI.text = bmiFormatted
        binding.tvStatusBMI.text = statusBMI
        binding.tvStatusBMI.setTextColor(android.graphics.Color.parseColor(warnaBMI))
        binding.tvTekananDarah.text = "$sistolik/$diastolik"
        binding.tvStatusTD.text = statusTD
        binding.tvStatusTD.setTextColor(android.graphics.Color.parseColor(warnaTD))
        binding.tvSaran.text = saranList.joinToString("\n\n")
        binding.cardHasil.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
