package com.example.bunga_floryn.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.bunga_floryn.R
import com.example.bunga_floryn.pertemuan_5.WebViewActivity

class CustomActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom1)

        val judul = intent.getStringExtra("judul_halaman") ?: "Specialties"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Find Your Doctor"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = judul
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        findViewById<TextView>(R.id.tvJudulHalaman).text = judul
        findViewById<TextView>(R.id.tvDeskripsiDariUtama).text = deskripsi

        findViewById<Button>(R.id.btnWebView).setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
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
