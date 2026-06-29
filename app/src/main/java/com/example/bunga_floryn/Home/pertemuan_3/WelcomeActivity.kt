package com.example.bunga_floryn.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.bunga_floryn.AuthActivity
import com.example.bunga_floryn.Home.pertemuan_4.CustomActivity1
import com.example.bunga_floryn.Home.pertemuan_4.CustomActivity2
import com.example.bunga_floryn.R
import com.example.bunga_floryn.utils.NotificationHelper
import com.example.bunga_floryn.utils.PermissionHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.example.bunga_floryn.Home.pertemuan_2.MainActivity as RumusBangunRuangActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var btnRumusBangunRuang: Button
    private lateinit var btnCustom1: Button
    private lateinit var btnCustom2: Button
    private lateinit var btnLogout: Button

    // Launcher untuk request permission notifikasi
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Izin notifikasi diberikan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Izin notifikasi ditolak. Aktifkan di Pengaturan.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Welcome"
            subtitle = "HealthTrack"
        }

        tvUsername = findViewById(R.id.tvUsername)
        btnRumusBangunRuang = findViewById(R.id.btnRumusBangunRuang)
        btnCustom1 = findViewById(R.id.btnCustom1)
        btnCustom2 = findViewById(R.id.btnCustom2)
        btnLogout = findViewById(R.id.btnLogout)

        // Ambil username dari SharedPreferences atau Intent
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", null)
            ?: intent.getStringExtra("USERNAME")
            ?: "User"
        tvUsername.text = username

        // Minta izin notifikasi saat halaman dibuka (Android 13+)
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        setupClickListeners(sharedPref, username)
    }

    private fun setupClickListeners(
        sharedPref: android.content.SharedPreferences,
        username: String
    ) {
        btnRumusBangunRuang.setOnClickListener {
            startActivity(Intent(this, RumusBangunRuangActivity::class.java).apply {
                putExtra("judul_halaman", "Rumus Bangun Ruang")
                putExtra("deskripsi", "Hitung luas segitiga dan volume bola")
            })
        }

        btnCustom1.setOnClickListener {
            // Kirim notifikasi lokal bahwa user membuka Cek Kesehatan
            val intent = Intent(this, CustomActivity1::class.java).apply {
                putExtra("judul_halaman", "Cek Kesehatan")
                putExtra("deskripsi", "Desain Figma dengan gambar ilustrasi kesehatan")
            }
            NotificationHelper.showNotification(
                context = this,
                title = "Cek Kesehatan",
                message = "Halo $username, jangan lupa rutin cek kesehatanmu!",
                intent = Intent(this, CustomActivity1::class.java).apply {
                    putExtra("judul_halaman", "Cek Kesehatan")
                    putExtra("deskripsi", "Desain Figma dengan gambar ilustrasi kesehatan")
                }
            )
            startActivity(intent)
        }

        btnCustom2.setOnClickListener {
            startActivity(Intent(this, CustomActivity2::class.java).apply {
                putExtra("judul_halaman", "Jadwal & Tips Sehat")
                putExtra("deskripsi", "Desain Figma dengan tema olahraga")
            })
        }

        btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()
                    startActivity(
                        Intent(this, AuthActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                    )
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
