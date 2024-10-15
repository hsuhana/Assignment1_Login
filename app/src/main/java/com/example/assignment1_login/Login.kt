package com.example.assignment1_login

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1_login.databinding.ActivityLoginBinding
import com.example.assignment1_login.databinding.ActivityMainBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            val enteredUsername = binding.etUsername.text.toString()
            val enteredPassword = binding.etPassword.text.toString()

            val sharedPreferences: SharedPreferences = getSharedPreferences("User", MODE_PRIVATE)
            val storedUsername = sharedPreferences.getString("USERNAME_$enteredUsername", null)
            val storedPassword = sharedPreferences.getString("PASSWORD_$enteredPassword", null)

            if(storedUsername == null){
                Toast.makeText(this, "Username does not exit.", Toast.LENGTH_SHORT).show()
            }else{
                if(storedPassword == enteredPassword){
                    Toast.makeText(this, "Log in successful.", Toast.LENGTH_SHORT).show()
                    val gitHubUrl = "https://pages.github.com/"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(gitHubUrl))
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnReturn.setOnClickListener{
            val intent = Intent(this, ActivityMainBinding :: class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}