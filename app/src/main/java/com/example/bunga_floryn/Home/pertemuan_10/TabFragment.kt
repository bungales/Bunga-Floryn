package com.example.bunga_floryn.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bunga_floryn.databinding.FragmentTabBinding

class TabFragment : Fragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!
    private var tabTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            tabTitle = it.getString(ARG_TAB_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = when (tabTitle) {
            "Fasilitas" -> getFasilitasData()
            "Kegiatan" -> getKegiatanData()
            else -> emptyList()
        }

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = DataAdapter(dataList)
    }

    private fun getFasilitasData(): List<DataModel> {
        return listOf(
            DataModel("Balai Desa", "Pusat Pemerintahan", "https://images.unsplash.com/photo-1517048676732-d65bc937f952?q=80&w=500"),
            DataModel("Puskesmas", "Layanan Kesehatan", "https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?q=80&w=500"),
            DataModel("Posyandu", "Kesehatan Anak", "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?q=80&w=500"),
            DataModel("Lapangan Bola", "Sarana Olahraga", "https://images.unsplash.com/photo-1574629810360-7efbbe195018?q=80&w=500"),
            DataModel("Sekolah Dasar", "Pendidikan Dasar", "https://images.unsplash.com/photo-1509062522246-3755977927d7?q=80&w=500"),
            DataModel("Perpustakaan", "Pusat Literasi", "https://images.unsplash.com/photo-1507842217343-583bb7270b66?q=80&w=500"),
            DataModel("Taman Desa", "Area Rekreasi", "https://images.unsplash.com/photo-1441974231531-c6227db76b6e?q=80&w=500"),
            DataModel("Kantor Polisi", "Keamanan Warga", "https://images.unsplash.com/photo-1450101499163-c8848c66ca85?q=80&w=500"),
            DataModel("Gedung Serbaguna", "Ruang Pertemuan", "https://images.unsplash.com/photo-1497366216548-37526070297c?q=80&w=500")
        )
    }

    private fun getKegiatanData(): List<DataModel> {
        return listOf(
            DataModel("Kerja Bakti", "Gotong Royong", "https://images.unsplash.com/photo-1593113598332-cd288d649433?q=80&w=500"),
            DataModel("Rapat Desa", "Musyawarah Warga", "https://images.unsplash.com/photo-1521737604893-d14cc237f11d?q=80&w=500"),
            DataModel("Posyandu Lansia", "Cek Kesehatan", "https://images.unsplash.com/photo-1581056771107-24ca5f033842?q=80&w=500"),
            DataModel("Latihan Bola", "Kegiatan Pemuda", "https://images.unsplash.com/photo-1517466787929-bc90951d0974?q=80&w=500"),
            DataModel("Senam Pagi", "Kesehatan Jasmani", "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?q=80&w=500"),
            DataModel("Festival Budaya", "Pelestarian Seni", "https://images.unsplash.com/photo-1533174072545-7a4b6ad7a6c3?q=80&w=500"),
            DataModel("Donor Darah", "Kegiatan Sosial", "https://images.unsplash.com/photo-1615461066841-6116e61058f4?q=80&w=500"),
            DataModel("Penyuluhan", "Edukasi Pertanian", "https://images.unsplash.com/photo-1523240795612-9a054b0db644?q=80&w=500"),
            DataModel("Pameran UMKM", "Promosi Produk", "https://images.unsplash.com/photo-1472851294608-062f824d29cc?q=80&w=500"),
            DataModel("Upacara Desa", "Peringatan Hari Besar", "https://images.unsplash.com/photo-1517457373958-b7bdd4587205?q=80&w=500"),

        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TAB_TITLE = "tab_title"

        @JvmStatic
        fun newInstance(title: String) =
            TabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TAB_TITLE, title)
                }
            }
    }
}