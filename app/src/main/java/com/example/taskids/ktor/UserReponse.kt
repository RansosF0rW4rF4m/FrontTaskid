package com.example.taskids.ktor

import kotlinx.serialization.Serializable

@Serializable
class UserResponse (
    val id: Int,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val user_type: String,
    val bio: String?
) {
}