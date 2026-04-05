package com.example.bunga_floryn.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_floryn.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Tombol Login
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("USERNAME", extractUsername(email))
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        // Tombol Sign Up
        binding.btnSignUp.setOnClickListener {
            Toast.makeText(this, "Sign Up feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Link Forgot Password
        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, "Forgot Password feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Link Sign Up (bawah)
        binding.tvSignUpLink.setOnClickListener {
            Toast.makeText(this, "Sign Up feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && email.contains("@")
    }

    private fun extractUsername(email: String): String {
        return email.substringBefore("@")
    }
}