package com.example.taskids.models

data class UserModel(
    val id: Int? = null,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val user_type: String,
    val bio: String? = null,
    val kids: List<Int> = emptyList(),
    val guardians: List<Int> = emptyList()
)