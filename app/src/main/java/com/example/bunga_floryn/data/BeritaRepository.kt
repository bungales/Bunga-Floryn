package com.example.bunga_floryn.data

import com.example.bunga_floryn.data.model.BeritaModel

object BeritaRepository {
    fun getBeritaDesa(): List<BeritaModel> = listOf(
        BeritaModel(
            id = 1,
            judul = "Renovasi Balai Desa Selesai, Warga Antusias Sambut Fasilitas Baru",
            deskripsi = "Program renovasi balai desa yang sudah lama dinantikan akhirnya rampung dikerjakan. Bangunan baru dilengkapi aula pertemuan, ruang administrasi, dan area parkir yang lebih luas untuk menunjang kegiatan masyarakat.",
            kategori = "Fasilitas Desa",
            tanggal = "05 Jun 2026",
            imageUrl = "https://picsum.photos/seed/balaidesa/600/300"
        ),
        BeritaModel(
            id = 2,
            judul = "Jalan Desa Diperbaiki, Akses Warga ke Pasar Semakin Lancar",
            deskripsi = "Perbaikan jalan sepanjang 2 kilometer yang menghubungkan pemukiman warga dengan pasar desa kini telah selesai. Pengaspalan ulang ini diharapkan memperlancar aktivitas ekonomi warga sehari-hari.",
            kategori = "Infrastruktur",
            tanggal = "02 Jun 2026",
            imageUrl = "https://picsum.photos/seed/jalandes/600/300"
        ),
        BeritaModel(
            id = 3,
            judul = "Posyandu Desa Kini Dilengkapi Peralatan Kesehatan Modern",
            deskripsi = "Dinas Kesehatan memberikan bantuan peralatan medis untuk posyandu desa. Alat pengukur tensi digital, timbangan bayi, dan kit pemeriksaan dasar kini tersedia untuk layanan kesehatan ibu dan anak.",
            kategori = "Kesehatan",
            tanggal = "28 Mei 2026",
            imageUrl = "https://picsum.photos/seed/posyandu/600/300"
        ),
        BeritaModel(
            id = 4,
            judul = "Embung Desa Selesai Dibangun, Solusi Irigasi Sawah Warga",
            deskripsi = "Pembangunan embung berkapasitas 5.000 meter kubik resmi selesai. Fasilitas penampungan air hujan ini akan mengairi ratusan hektar sawah di musim kemarau dan mengurangi risiko banjir saat musim hujan.",
            kategori = "Pertanian",
            tanggal = "20 Mei 2026",
            imageUrl = "https://picsum.photos/seed/embung/600/300"
        ),
        BeritaModel(
            id = 5,
            judul = "Taman Bermain Anak Baru Hadir di Pusat Desa",
            deskripsi = "Pemerintah desa meresmikan taman bermain anak yang dibangun dari Dana Desa. Area bermain ini dilengkapi ayunan, perosotan, dan zona belajar outdoor untuk mendukung tumbuh kembang anak-anak desa.",
            kategori = "Fasilitas Publik",
            tanggal = "15 Mei 2026",
            imageUrl = "https://picsum.photos/seed/taman/600/300"
        ),
        BeritaModel(
            id = 6,
            judul = "Sumur Bor Baru Atasi Krisis Air Bersih di RT 04",
            deskripsi = "Pembangunan sumur bor sedalam 60 meter berhasil mengatasi masalah kekurangan air bersih yang dialami warga RT 04 selama bertahun-tahun. Kini 45 KK dapat menikmati air bersih yang layak konsumsi.",
            kategori = "Air & Sanitasi",
            tanggal = "10 Mei 2026",
            imageUrl = "https://picsum.photos/seed/sumurbor/600/300"
        ),
        BeritaModel(
            id = 7,
            judul = "Perpustakaan Desa Dibuka, Akses Buku Gratis untuk Semua Warga",
            deskripsi = "Ruang perpustakaan desa kini resmi dibuka dengan koleksi lebih dari 500 buku. Warga dari segala usia dapat meminjam buku secara gratis untuk mendukung gerakan literasi di tingkat desa.",
            kategori = "Pendidikan",
            tanggal = "05 Mei 2026",
            imageUrl = "https://picsum.photos/seed/perpustakaan/600/300"
        )
    )
}
