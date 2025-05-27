package com.example.taskids.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    val id: Int,
    val username: String,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("user_type") val userType: String,
    val bio: String?,
    val kids: List<Int>?,
    val guardians: List<Int>?
)