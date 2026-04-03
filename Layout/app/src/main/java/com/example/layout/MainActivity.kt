package com.example.layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    lateinit var editTextUsername: EditText
//    lateinit var editTextPassword: EditText
//    lateinit var btnLogin: Button

//    fun initUI() {
//        editTextUsername = findViewById<EditText>(R.id.edit_text_username)
//        editTextPassword = findViewById<EditText>(R.id.edit_text_password)
//        btnLogin = findViewById<Button>(R.id.btn_login)
//    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        btnLogin.setOnClickListener { view ->
//            if (!editTextUsername.text.isNullOrEmpty() && !editTextPassword.text.isNullOrEmpty()) println(
//                "Result : Success (Username : ${editTextUsername.text}, Password : ${editTextPassword.text})"
//            )
//            else println("Result : Failed")
//        }

        binding.btnLogin.setOnClickListener { view ->
            if (!binding.username.isNullOrEmpty() && !binding.password.isNullOrEmpty()) println(
                "Result : Success (Username ${binding.username}: Password : ${binding.password})"
            ) else println("Result : Failed")
        }
    }
}