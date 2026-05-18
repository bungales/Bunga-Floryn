package com.example.bunga_floryn.Home.pertemuan_9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_floryn.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan 9"
            setDisplayHomeAsUpEnabled(true)
        }

        // Chip & ChipGroup listener
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // MaterialButton Login dengan validasi
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            binding.textInputLayout.error = null
            binding.tilPassword.error = null

            var valid = true
            if (email.isEmpty() || !email.contains("@")) {
                binding.textInputLayout.error = "Email tidak valid"
                valid = false
            }
            if (password.isEmpty() || password.length < 6) {
                binding.tilPassword.error = "Password minimal 6 karakter"
                valid = false
            }
            if (valid) {
                Snackbar.make(binding.root, "Login berhasil: $email", Snackbar.LENGTH_SHORT).show()
            }
        }

        // GridLayout button clicks
        val gridButtons = listOf(
            binding.btnMenu1 to "Beranda",
            binding.btnMenu2 to "Profil",
            binding.btnMenu3 to "Notifikasi",
            binding.btnMenu4 to "Riwayat",
            binding.btnMenu5 to "Pengaturan",
            binding.btnMenu6 to "Bantuan"
        )
        gridButtons.forEach { (btn, label) ->
            btn.setOnClickListener {
                Toast.makeText(this, "Menu: $label", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
