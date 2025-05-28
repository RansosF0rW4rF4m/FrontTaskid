package com.example.taskids.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class UserType{
    @SerialName("guardian")
    GUARDIAN,

    @SerialName("kid")
    KID
}

@Serializable
data class UserModel(
    val id: Int,
    val username: String?,
    val email: String?,
    @SerialName("first_name") val firstName: String?,
    @SerialName("last_name") val lastName: String?,
    @SerialName("user_type") val userType: UserType?, // "kid" ou "guardian"
    val bio: String? = null,
    val kids: List<Int>,   // Só preenchido se for Guardian
    val guardians: List<Int>  // Só preenchido se for Kid
)