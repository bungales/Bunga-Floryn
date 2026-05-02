package com.example.bunga_floryn.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bunga_floryn.AuthActivity
import com.example.bunga_floryn.databinding.FragmentHomeBinding
import com.example.bunga_floryn.Home.pertemuan_2.MainActivity as RumusBangunRuangActivity
import com.example.bunga_floryn.Home.pertemuan_4.CustomActivity1
import com.example.bunga_floryn.Home.pertemuan_4.CustomActivity2
import com.example.bunga_floryn.Home.pertemuan_5.WebViewActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }
        // Tampilkan username dari SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User") ?: "User"
        binding.tvUsername.text = username
        binding.btnRumusBangunRuang.setOnClickListener {
            val intent = Intent(requireContext(), RumusBangunRuangActivity::class.java)
            intent.putExtra("judul_halaman", "Rumus Bangun Ruang")
            intent.putExtra("deskripsi", "Hitung luas segitiga dan volume bola")
            startActivity(intent)
        }
        binding.btnCustom1.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity1::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 1")
            intent.putExtra("deskripsi", "Desain Figma dengan gambar ilustrasi kesehatan")
            startActivity(intent)
        }
        binding.btnCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity2::class.java)
            intent.putExtra("judul_halaman", "Custom Screen 2")
            intent.putExtra("deskripsi", "Desain Figma dengan tema olahraga")
            startActivity(intent)
        }
        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(requireView(), "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
