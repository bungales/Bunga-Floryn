package com.example.bunga_floryn.Settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bunga_floryn.R
import com.example.bunga_floryn.databinding.FragmentSettingsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val settingsList = listOf(
        SettingsModel("Akun Saya", "Kelola informasi akun dan keamanan", R.drawable.ic_profile),
        SettingsModel("Notifikasi", "Atur preferensi notifikasi aplikasi", R.drawable.ic_home),
        SettingsModel("Privasi & Keamanan", "Kebijakan privasi dan pengaturan keamanan", R.drawable.ic_about),
        SettingsModel("Tampilan", "Tema, ukuran font, dan bahasa", R.drawable.ic_settings),
        SettingsModel("Bantuan & FAQ", "Pertanyaan umum dan panduan penggunaan", R.drawable.ic_about),
        SettingsModel("Hubungi Kami", "Kirim masukan atau laporkan masalah", R.drawable.ic_profile),
        SettingsModel("Tentang Aplikasi", "Versi aplikasi, lisensi, dan informasi developer", R.drawable.ic_home),
        SettingsModel("Syarat & Ketentuan", "Baca syarat penggunaan layanan", R.drawable.ic_about),
        SettingsModel("Kebijakan Cookie", "Penggunaan cookie dalam aplikasi", R.drawable.ic_settings),
        SettingsModel("Hapus Akun", "Hapus akun dan semua data secara permanen", R.drawable.ic_profile)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pengaturan"
        }
        val adapter = SettingsAdapter(requireContext(), settingsList)
        binding.listViewSettings.adapter = adapter
        binding.listViewSettings.setOnItemClickListener { _, _, position, _ ->
            val item = settingsList[position]
            when (item.title) {
                "Hapus Akun" -> {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Hapus Akun")
                        .setMessage("Apakah Anda yakin ingin menghapus akun? Tindakan ini tidak dapat dibatalkan.")
                        .setPositiveButton("Hapus") { _, _ ->
                            Snackbar.make(binding.root, "Fitur ini belum tersedia", Snackbar.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Batal", null)
                        .show()
                }
                else -> {
                    Snackbar.make(binding.root, "${item.title}: Fitur segera hadir", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
