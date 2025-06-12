package com.example.taskids.data

data class RegisterRequest(
    val username: String,
    val password: String,
    val user_type: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val token: String,
    val user_id: Int,
    val username: String,
    val user_type: String
)