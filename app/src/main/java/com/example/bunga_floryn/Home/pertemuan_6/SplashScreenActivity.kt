package com.example.bunga_floryn.Home.pertemuan_6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bunga_floryn.AuthActivity
import com.example.bunga_floryn.BaseActivity
import com.example.bunga_floryn.Onboarding.OnboardingActivity
import com.example.bunga_floryn.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)
        val onboardingDone = sharedPref.getBoolean("onboarding_done", false)
        lifecycleScope.launch {
            delay(2000)
            when {
                isLogin -> startActivity(Intent(this@SplashScreenActivity, BaseActivity::class.java))
                onboardingDone -> startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
                else -> startActivity(Intent(this@SplashScreenActivity, OnboardingActivity::class.java))
            }
            finish()
        }
    }
}
