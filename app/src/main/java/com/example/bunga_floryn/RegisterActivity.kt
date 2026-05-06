package com.example.bunga_floryn

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bunga_floryn.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatePicker()
        setupReligionDropdown()

        binding.btnRegister.setOnClickListener {
            performRegistration()
        }

        binding.tvLoginLink.setOnClickListener {
            finish() // Go back to Login
        }
    }

    private fun setupDatePicker() {
        binding.etBirthDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    binding.etBirthDate.setText(date)
                    binding.tilBirthDate.error = null
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun setupReligionDropdown() {
        val religions = arrayOf("Islam", "Kristen", "Katolik", "Hindu", "Budha", "Konghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, religions)
        binding.actvReligion.setAdapter(adapter)
        binding.actvReligion.setOnItemClickListener { _, _, _, _ ->
            binding.tilReligion.error = null
        }
    }

    private fun performRegistration() {
        // Reset Errors
        binding.tilName.error = null
        binding.tilBirthDate.error = null
        binding.tilReligion.error = null
        binding.tilUsername.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
        binding.tvGenderError.visibility = View.GONE

        val name = binding.etName.text.toString().trim()
        val birthDate = binding.etBirthDate.text.toString().trim()
        val religion = binding.actvReligion.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        val genderId = binding.rgGender.checkedRadioButtonId

        var isValid = true

        if (name.isEmpty()) {
            binding.tilName.error = "Nama tidak boleh kosong"
            isValid = false
        }

        if (birthDate.isEmpty()) {
            binding.tilBirthDate.error = "Tanggal lahir tidak boleh kosong"
            isValid = false
        }

        if (genderId == -1) {
            binding.tvGenderError.text = "Pilih jenis kelamin"
            binding.tvGenderError.visibility = View.VISIBLE
            isValid = false
        }

        if (religion.isEmpty()) {
            binding.tilReligion.error = "Pilih agama"
            isValid = false
        }

        if (username.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            binding.tilPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Password tidak cocok"
            isValid = false
        }

        if (isValid) {
            // Save to SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            
            val gender = if (genderId == R.id.rbMale) "Laki-laki" else "Perempuan"

            editor.putString("reg_name", name)
            editor.putString("reg_birthdate", birthDate)
            editor.putString("reg_gender", gender)
            editor.putString("reg_religion", religion)
            editor.putString("reg_username", username)
            editor.putString("reg_password", password)
            editor.apply()

            Toast.makeText(this, "Registrasi Berhasil! Silakan Login", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
