package com.example.bunga_floryn.Warga

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bunga_floryn.data.AppDatabase
import com.example.bunga_floryn.data.entity.WargaEntity
import com.example.bunga_floryn.databinding.ActivityWargaFormBinding
import kotlinx.coroutines.launch

class WargaFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWargaFormBinding
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWargaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tambah Warga"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        db = AppDatabase.getInstance(this)

        binding.btnSaveWarga.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val nik = binding.etNik.text.toString()
            val alamat = binding.etAlamat.text.toString()
            val noTelp = binding.etNoTelp.text.toString()

            if (nama.isNotBlank() && nik.isNotBlank() && alamat.isNotBlank()) {
                lifecycleScope.launch {
                    val warga = WargaEntity(
                        nama = nama,
                        nik = nik,
                        alamat = alamat,
                        noTelp = noTelp
                    )
                    db.wargaDao().insert(warga)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi kolom nama, NIK, dan alamat!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
