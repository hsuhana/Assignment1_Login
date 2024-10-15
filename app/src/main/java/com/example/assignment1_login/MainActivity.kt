package com.example.assignment1_login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1_login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSignup.setOnClickListener{
            val intent = Intent(this, SignUp :: class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this, Login :: class.java)
            startActivity(intent)
        }

        binding.btnSignup2.setOnClickListener{
            val intent = Intent(this, SignUp2 :: class.java)
            startActivity(intent)
        }

        binding.btnLogin2.setOnClickListener{
            val intent = Intent(this, Login2 :: class.java)
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener{
            val updatesUrl = "https://github.blog/changelog/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updatesUrl))
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}