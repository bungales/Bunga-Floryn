package com.example.bunga_floryn.Home.pertemuan_2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.bunga_floryn.R
import com.example.bunga_floryn.Home.pertemuan_5.WebViewActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    private lateinit var etAlasSegitiga: TextInputEditText
    private lateinit var etTinggiSegitiga: TextInputEditText
    private lateinit var etJariBola: TextInputEditText
    private lateinit var tvHasilSegitiga: TextView
    private lateinit var tvHasilBola: TextView
    private lateinit var cardHasilSegitiga: View
    private lateinit var cardHasilBola: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Rumus Bangun Ruang"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        etAlasSegitiga = findViewById(R.id.et_alas_segitiga)
        etTinggiSegitiga = findViewById(R.id.et_tinggi_segitiga)
        etJariBola = findViewById(R.id.et_jari_bola)
        tvHasilSegitiga = findViewById(R.id.tv_hasil_segitiga_text)
        tvHasilBola = findViewById(R.id.tv_hasil_bola_text)
        cardHasilSegitiga = findViewById(R.id.tv_hasil_segitiga)
        cardHasilBola = findViewById(R.id.tv_hasil_bola)

        findViewById<android.widget.Button>(R.id.btn_hitung_segitiga).setOnClickListener { hitungLuasSegitiga() }
        findViewById<android.widget.Button>(R.id.btn_hitung_bola).setOnClickListener { hitungVolumeBola() }
        findViewById<android.widget.Button>(R.id.btnKembali).setOnClickListener { finish() }
        findViewById<android.widget.Button>(R.id.btnWebView).setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { onBackPressedDispatcher.onBackPressed(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun hitungLuasSegitiga() {
        val alasStr = etAlasSegitiga.text.toString().trim()
        val tinggiStr = etTinggiSegitiga.text.toString().trim()
        if (alasStr.isEmpty() || tinggiStr.isEmpty()) {
            Toast.makeText(this, "Mohon isi semua inputan!", Toast.LENGTH_SHORT).show()
            return
        }
        val alas = alasStr.toDoubleOrNull()
        val tinggi = tinggiStr.toDoubleOrNull()
        if (alas == null || tinggi == null || alas <= 0 || tinggi <= 0) {
            Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
            return
        }
        val luas = 0.5 * alas * tinggi
        tvHasilSegitiga.text = String.format("Luas Segitiga = %.2f cm²", luas)
        cardHasilSegitiga.visibility = View.VISIBLE
    }

    private fun hitungVolumeBola() {
        val jariStr = etJariBola.text.toString().trim()
        if (jariStr.isEmpty()) {
            Toast.makeText(this, "Mohon isi jari-jari bola!", Toast.LENGTH_SHORT).show()
            return
        }
        val r = jariStr.toDoubleOrNull()
        if (r == null || r <= 0) {
            Toast.makeText(this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
            return
        }
        val volume = (4.0 / 3.0) * PI * Math.pow(r, 3.0)
        tvHasilBola.text = String.format("Volume Bola = %.2f cm³", volume)
        cardHasilBola.visibility = View.VISIBLE
    }
}
