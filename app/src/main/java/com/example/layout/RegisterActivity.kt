package com.example.layout

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.layout.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterNow.setOnClickListener {
            val user = binding.etRegUser.text.toString()
            val pass = binding.etRegPass.text.toString()
            val email = binding.etRegEmail.text.toString()
            val phone = binding.etRegPhone.text.toString()
            val confirmPass = binding.etRegConfirmPass.text.toString()

            if (email.isEmpty() || phone.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Harus diisi semua form!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != confirmPass) {
                Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (user.isNotEmpty() && pass.isNotEmpty() && email.isNotEmpty()) {
                // Simpan data ke SharedPreferences
                val sharedPref = getSharedPreferences("UserAcc", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("saved_user", user)
                editor.putString("saved_pass", pass)
                editor.apply() // Data tersimpan permanen di HP

                Toast.makeText(this, "Registrasi Berhasil! Silakan Login", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke halaman Login
            } else {
                Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}