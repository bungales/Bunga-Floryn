package com.example.bunga_floryn.pertemuan_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.bunga_floryn.R
import com.example.bunga_floryn.pertemuan_2.MainActivity as RumusBangunRuangActivity
import com.example.bunga_floryn.pertemuan_4.CustomActivity1
import com.example.bunga_floryn.pertemuan_4.CustomActivity2
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var btnRumusBangunRuang: Button
    private lateinit var btnCustom1: Button
    private lateinit var btnCustom2: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        android.util.Log.e("onCreate", "WelcomeActivity dibuat pertama kali")

        // Inisialisasi View
        tvUsername = findViewById(R.id.tvUsername)
        btnRumusBangunRuang = findViewById(R.id.btnRumusBangunRuang)
        btnCustom1 = findViewById(R.id.btnCustom1)
        btnCustom2 = findViewById(R.id.btnCustom2)
        btnLogout = findViewById(R.id.btnLogout)

        // Ambil data username dari LoginActivity
        val username = intent.getStringExtra("USERNAME") ?: "User"
        tvUsername.text = username

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Tombol 1: ke halaman rumus bangun ruang
        btnRumusBangunRuang.setOnClickListener {
            val intent = Intent(this, RumusBangunRuangActivity::class.java)
            // Kirim data judul dan deskripsi (sesuai tugas)
            intent.putExtra("judul_halaman", "Rumus Bangun Ruang")
            intent.putExtra("deskripsi", "Hitung luas segitiga dan volume bola")
            startActivity(intent)
        }

        // Tombol 2: ke custom screen 1
        btnCustom1.setOnClickListener {
            val intent = Intent(this, CustomActivity1::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 1")
            intent.putExtra("deskripsi", "Desain Figma dengan gambar ilustrasi kesehatan")
            startActivity(intent)
        }

        // Tombol 3: ke custom screen 2
        btnCustom2.setOnClickListener {
            val intent = Intent(this, CustomActivity2::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 2")
            intent.putExtra("deskripsi", "Desain Figma dengan tema olahraga")
            startActivity(intent)
        }

        // Tombol 4: Logout dengan AlertDialog (sesuai materi dosen)
        btnLogout.setOnClickListener {
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        // Menggunakan MaterialAlertDialogBuilder seperti materi dosen
        com.google.android.material.dialog.MaterialAlertDialogBuilder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                android.util.Log.e("Info Dialog", "Anda memilih Ya, logout!")
                // Pindah ke LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
                android.util.Log.e("Info Dialog", "Anda memilih Tidak!")
                // Tampilkan Snackbar (sesuai materi dosen)
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Logout dibatalkan",
                    Snackbar.LENGTH_SHORT
                ).setAction("Tutup") {
                    android.util.Log.e("Info Snackbar", "Snackbar ditutup")
                }.show()
            }
            .show()
    }

    // Lifecycle Logs sesuai materi dosen
    override fun onStart() {
        super.onStart()
        android.util.Log.e("onStart", "WelcomeActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.e("onDestroy", "WelcomeActivity dihapus dari stack")
    }
}