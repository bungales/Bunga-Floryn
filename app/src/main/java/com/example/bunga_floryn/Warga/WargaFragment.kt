package com.example.bunga_floryn.Warga

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bunga_floryn.data.AppDatabase
import com.example.bunga_floryn.data.entity.WargaEntity
import com.example.bunga_floryn.databinding.FragmentWargaBinding
import kotlinx.coroutines.launch

class WargaFragment : Fragment() {
    private var _binding: FragmentWargaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: WargaRoomAdapter
    private lateinit var db: AppDatabase
    private val wargaList = mutableListOf<WargaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWargaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Data Warga"
        }

        db = AppDatabase.getInstance(requireContext())
        adapter = WargaRoomAdapter(wargaList, this)
        binding.rvWarga.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWarga.adapter = adapter
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvWarga.addItemDecoration(divider)
        fetchWarga()
        binding.fabAddWarga.setOnClickListener {
            startActivity(Intent(requireContext(), WargaFormActivity::class.java))
        }
    }
    private fun fetchWarga() {
        lifecycleScope.launch {
            val data = db.wargaDao().getAll()
            wargaList.clear()
            wargaList.addAll(data)
            adapter.notifyDataSetChanged()
            binding.tvJumlah.text = "${wargaList.size} warga terdaftar"
        }
    }
    override fun onResume() {
        super.onResume()
        fetchWarga()
    }

    fun deleteWarga(warga: WargaEntity) {
        lifecycleScope.launch {
            db.wargaDao().delete(warga)
            fetchWarga()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
