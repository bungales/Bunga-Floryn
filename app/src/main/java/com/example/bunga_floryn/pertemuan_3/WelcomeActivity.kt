package com.example.bunga_floryn.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import android.widget.TextView
import com.example.bunga_floryn.R
import com.example.bunga_floryn.pertemuan_2.MainActivity as RumusBangunRuangActivity
import com.example.bunga_floryn.pertemuan_4.CustomActivity1
import com.example.bunga_floryn.pertemuan_4.CustomActivity2
import com.example.bunga_floryn.AuthActivity
import com.example.bunga_floryn.pertemuan_5.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var btnRumusBangunRuang: Button
    private lateinit var btnCustom1: Button
    private lateinit var btnCustom2: Button
    private lateinit var btnLogout: Button
    private lateinit var btnPertemuan5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Welcome"
            subtitle = "RuangKu"
        }

        tvUsername = findViewById(R.id.tvUsername)
        btnRumusBangunRuang = findViewById(R.id.btnRumusBangunRuang)
        btnCustom1 = findViewById(R.id.btnCustom1)
        btnCustom2 = findViewById(R.id.btnCustom2)
        btnLogout = findViewById(R.id.btnLogout)
        btnPertemuan5 = findViewById(R.id.btnPertemuan5)

        // Ambil username dari SharedPreferences atau Intent
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", null)
            ?: intent.getStringExtra("USERNAME")
            ?: "User"
        tvUsername.text = username

        setupClickListeners(sharedPref)
    }

    private fun setupClickListeners(sharedPref: android.content.SharedPreferences) {
        btnRumusBangunRuang.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            intent.putExtra("judul_halaman", "Rumus Bangun Ruang")
            intent.putExtra("deskripsi", "Hitung luas segitiga dan volume bola")
            startActivity(intent)
        }

        btnCustom1.setOnClickListener {
            val intent = Intent(this, CustomActivity1::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 1")
            intent.putExtra("deskripsi", "Desain Figma dengan gambar ilustrasi kesehatan")
            startActivity(intent)
        }

        btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomActivity2::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 2")
            intent.putExtra("deskripsi", "Desain Figma dengan tema olahraga")
            startActivity(intent)
        }

        btnPertemuan5.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus SharedPreferences
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Logout dibatalkan",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                .show()
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
