package com.example.taskids.ui.theme.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.taskids.R
import com.example.taskids.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val repo = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            lifecycleScope.launch {
                val response = repo.login(username, password)
                if (response.isSuccessful) {
                    val token = response.body()?.get("auth_token") ?: ""
                    if (token.isNotEmpty()) {
                        getSharedPreferences("app", MODE_PRIVATE).edit()
                            .putString("token", token)
                            .apply()
                        Toast.makeText(this@LoginActivity, "Login realizado!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Token vazio", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login falhou", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}