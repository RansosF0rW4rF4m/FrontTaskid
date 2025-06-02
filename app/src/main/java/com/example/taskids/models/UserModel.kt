package com.example.taskids.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val id: Int,
    val username: String?,
    val email: String?,
    val first_name: String?,
    @SerialName("last_name") val last_name: String?,
    @SerialName("user_type") val user_type: String?, // "kid" ou "guardian"
    val bio: String? = null,
    val kids: List<Int>,   // Só preenchido se for Guardian
    val guardians: List<Int>  // Só preenchido se for Kid
)