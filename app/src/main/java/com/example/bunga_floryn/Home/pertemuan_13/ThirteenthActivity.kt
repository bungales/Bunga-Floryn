package com.example.bunga_floryn.Home.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bunga_floryn.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding
    private val tabTitles = arrayOf("Capture", "QR Code", "Scan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan 13: Camera"
            setDisplayHomeAsUpEnabled(true)
        }

        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    inner class ThirteenthTabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TabCaptureFragment()
                1 -> TabQrcodeFragment()
                else -> TabScanFragment()
            }
        }
    }
}
