package com.example.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener { view ->
            val inputUser = binding.editTextUsername.text.toString()
            val inputPass = binding.editTextPassword.text.toString()
            val sharedPref = getSharedPreferences("UserAcc", MODE_PRIVATE)
            val savedUser = sharedPref.getString("saved_user", null)
            val savedPass = sharedPref.getString("saved_pass", null)

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                Toast.makeText(this, "Isi Username & Password dulu!", Toast.LENGTH_SHORT).show()
            } else if (inputUser == savedUser && inputPass == savedPass && savedUser != null) {
                // Login Berhasil [cite: 62]
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }

            else {
                Toast.makeText(this, "Akun tidak ditemukan! Silakan daftar.", Toast.LENGTH_SHORT).show()
            }


            }
        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}