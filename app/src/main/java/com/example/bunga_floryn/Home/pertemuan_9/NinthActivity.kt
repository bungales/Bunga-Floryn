package com.example.bunga_floryn.Home.pertemuan_9

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_floryn.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    // Data warga sesuai aplikasi Bina Desa
    private val allWarga = listOf(
        WargaModel("Rizky Ramadhan",  "1471020304950011", "L", "Islam",   "Petani",        "081345678901", "rizky.ramadhan@email.com",  "Jl. Sudirman No. 3, RT 02/RW 01"),
        WargaModel("Nurul Azizah",    "1471025506980012", "P", "Islam",   "Bidan",         "082456789012", "nurul.azizah@email.com",    "Jl. Diponegoro No. 7, RT 01/RW 03"),
        WargaModel("Fajar Kurniawan", "1471020607870013", "L", "Islam",   "Nelayan",       "083567890123", "fajar.kurniawan@email.com", "Jl. Imam Bonjol No. 4, RT 04/RW 02"),
        WargaModel("Rini Oktaviani",  "1471029908910014", "P", "Kristen", "Dokter",        "084678901234", "rini.oktaviani@email.com",  "Jl. Gatot Subroto No. 11, RT 03/RW 05"),
        WargaModel("Dimas Aditya",    "1471021009800015", "L", "Islam",   "Teknisi",       "085789012345", "dimas.aditya@email.com",    "Jl. Ahmad Yani No. 6, RT 05/RW 04"),
        WargaModel("Putri Maharani",  "1471021110960016", "P", "Islam",   "Apoteker",      "086890123456", "putri.maharani@email.com",  "Jl. Veteran No. 9, RT 02/RW 06"),
        WargaModel("Hendri Saputra",  "1471021211830017", "L", "Budha",   "Sopir",         "087901234567", "hendri.saputra@email.com",  "Jl. Kartini No. 2, RT 06/RW 01"),
        WargaModel("Yuliana Putri",   "1471021312900018", "P", "Islam",   "Karyawan",      "088012345678", "yuliana.putri@email.com",   "Jl. Hasanuddin No. 14, RT 01/RW 07"),
        WargaModel("Arif Hidayat",    "1471021413010019", "L", "Islam",   "Guru Honorer",  "089123456789", "arif.hidayat@email.com",    "Jl. Panjaitan No. 5, RT 07/RW 03"),
        WargaModel("Mega Wulandari",  "1471021514920020", "P", "Islam",   "Pedagang",      "081234509876", "mega.wulandari@email.com",  "Jl. Pattimura No. 8, RT 03/RW 08")
    )

    private var filteredWarga = allWarga.toMutableList()
    private lateinit var adapter: WargaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Data Warga"
            setDisplayHomeAsUpEnabled(true)
        }

        // Update stat chips sesuai data
        updateStatChips()

        // Setup ListView
        adapter = WargaAdapter(this, filteredWarga)
        binding.listViewWarga.adapter = adapter

        // Klik item warga → tampilkan detail
        binding.listViewWarga.setOnItemClickListener { _, _, position, _ ->
            val w = filteredWarga[position]
            Snackbar.make(
                binding.root,
                "${w.nama} | ${w.noHp} | ${w.alamat}",
                Snackbar.LENGTH_LONG
            ).show()
        }

        // TextInputLayout search — filter real-time
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterWarga(s.toString(), getSelectedGender())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // ChipGroup filter jenis kelamin
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val id = checkedIds.firstOrNull()
            if (id != null) {
                val chip = group.findViewById<Chip>(id)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
            filterWarga(binding.etSearch.text.toString(), getSelectedGender())
        }
    }

    private fun getSelectedGender(): String = when (binding.chipGroupFilter.checkedChipId) {
        binding.chipLaki.id      -> "L"
        binding.chipPerempuan.id -> "P"
        else                     -> "Semua"
    }

    private fun filterWarga(query: String, gender: String) {
        filteredWarga.clear()
        allWarga.filter { w ->
            val matchQuery = query.isEmpty() ||
                w.nama.contains(query, ignoreCase = true) ||
                w.nik.contains(query) ||
                w.alamat.contains(query, ignoreCase = true)
            val matchGender = gender == "Semua" || w.gender == gender
            matchQuery && matchGender
        }.also { filteredWarga.addAll(it) }

        adapter.notifyDataSetChanged()
        binding.tvJumlahData.text = "${filteredWarga.size} data"
        updateStatChips()
    }
    private fun updateStatChips() {
        val total      = filteredWarga.size
        val laki       = filteredWarga.count { it.gender == "L" }
        val perempuan  = filteredWarga.count { it.gender == "P" }
        binding.chipTotal.text          = "$total Total"
        binding.chipLakiStat.text       = "$laki Laki-laki"
        binding.chipPerempuanStat.text  = "$perempuan Perempuan"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
