package com.example.bunga_floryn.Onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.bunga_floryn.AuthActivity
import com.example.bunga_floryn.R
import com.example.bunga_floryn.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var dots: Array<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OnboardingAdapter(this)
        binding.onboardingViewPager.adapter = adapter

        setupDots(adapter.itemCount, 0)

        binding.onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setupDots(adapter.itemCount, position)
                if (position == adapter.itemCount - 1) {
                    binding.btnNext.text = "Ayo Mulai"
                    binding.tvSkip.visibility = View.GONE
                } else {
                    binding.btnNext.text = "Selanjutnya"
                    binding.tvSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val current = binding.onboardingViewPager.currentItem
            if (current < adapter.itemCount - 1) {
                binding.onboardingViewPager.currentItem = current + 1
            } else {
                goToLogin()
            }
        }

        binding.tvSkip.setOnClickListener { goToLogin() }
    }

    private fun setupDots(count: Int, active: Int) {
        binding.dotsIndicator.removeAllViews()
        dots = Array(count) { ImageView(this) }
        val params = LinearLayout.LayoutParams(16, 16).apply { setMargins(6, 0, 6, 0) }
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i].layoutParams = params
            dots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (i == active) R.drawable.dot_active else R.drawable.dot_inactive
                )
            )
            binding.dotsIndicator.addView(dots[i])
        }
    }
    private fun goToLogin() {
        getSharedPreferences("user_pref", MODE_PRIVATE)
            .edit().putBoolean("onboarding_done", true).apply()
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}
