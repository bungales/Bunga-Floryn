package com.example.bunga_floryn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bunga_floryn.About.AboutFragment
import com.example.bunga_floryn.Home.HomeFragment
import com.example.bunga_floryn.Note.NoteFragment
import com.example.bunga_floryn.Profile.ProfileFragment
import com.example.bunga_floryn.Settings.SettingsFragment
import com.example.bunga_floryn.Warga.WargaFragment
import com.example.bunga_floryn.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        replaceFragment(HomeFragment())
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> { replaceFragment(HomeFragment()); true }
                R.id.note -> { replaceFragment(NoteFragment()); true }
                R.id.warga -> { replaceFragment(WargaFragment()); true }
                R.id.profile -> { replaceFragment(ProfileFragment()); true }
                R.id.settings -> { replaceFragment(SettingsFragment()); true }
                R.id.about -> { replaceFragment(AboutFragment()); true }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
