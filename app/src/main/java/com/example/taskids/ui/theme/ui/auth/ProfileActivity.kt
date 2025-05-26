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
import com.example.taskids.models.UserModel
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private val repo = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val token = getSharedPreferences("app", MODE_PRIVATE).getString("token", "") ?: ""

        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        lifecycleScope.launch {
            val response = repo.getProfile(token)
            if (response.isSuccessful) {
                val user = response.body()
                user?.let {
                    edtUsername.setText(it.username)
                    edtEmail.setText(it.email)
                }
            } else {
                Toast.makeText(this@ProfileActivity, "Erro ao buscar perfil", Toast.LENGTH_SHORT).show()
            }
        }

        btnSave.setOnClickListener {
            val updatedUser = UserModel(
                username = edtUsername.text.toString(),
                email = edtEmail.text.toString()
            )
            lifecycleScope.launch {
                val response = repo.updateProfile(token, updatedUser)
                if (response.isSuccessful) {
                    Toast.makeText(this@ProfileActivity, "Perfil atualizado!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ProfileActivity, "Erro ao atualizar", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnDelete.setOnClickListener {
            lifecycleScope.launch {
                val response = repo.deleteUser(token)
                if (response.isSuccessful) {
                    getSharedPreferences("app", MODE_PRIVATE).edit().remove("token").apply()
                    Toast.makeText(this@ProfileActivity, "Conta excluída", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@ProfileActivity, "Erro ao excluir conta", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}