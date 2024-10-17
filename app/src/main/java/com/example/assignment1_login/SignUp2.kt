package com.example.assignment1_login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1_login.databinding.ActivityMainBinding
import com.example.assignment1_login.databinding.ActivitySignUp2Binding
import java.util.regex.Pattern

class SignUp2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUp2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnSignup.setOnClickListener{
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            // Regular expression for password validation
            val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{8,15}$")

            if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }else if(!passwordPattern.containsMatchIn(password)){
                Toast.makeText(this, "Password must be 8-15 long, include at least one capital letter and one special character.", Toast.LENGTH_LONG).show()
            }else if(password != confirmPassword){
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
            }else{
                val sharedPreferences: SharedPreferences = getSharedPreferences("User", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()

                val inputUsername = sharedPreferences.getString("USERNAME_$username", null)

                if(inputUsername != null){
                    Toast.makeText(this, "Username already exists. Choose another username.", Toast.LENGTH_SHORT).show()
                }else{
                    editor.putString("USERNAME_$username", username)
                    editor.putString("PASSWORD_$password", password)
                    editor.apply()

                    Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnReturn.setOnClickListener{
            val intent = Intent(this, ActivityMainBinding ::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}