package com.example.taskids.data.repository

import com.example.taskids.data.network.ApiClient
import com.example.taskids.data.network.UserService
import com.example.taskids.models.UserModel
import retrofit2.Response

class UserRepository {
    private val service = ApiClient.retrofit.create(UserService::class.java)

    suspend fun login(username: String, password: String): Response<Map<String, String>> {
        return service.login(mapOf("username" to username, "password" to password))
    }

    suspend fun register(user: UserModel): Response<UserModel> {
        return service.register(user)
    }

    suspend fun getProfile(token: String): Response<UserModel> {
        return service.getProfile("Token $token")
    }

    suspend fun updateProfile(token: String, user: UserModel): Response<UserModel> {
        return service.updateProfile("Token $token", user)
    }

    suspend fun deleteUser(token: String): Response<Unit> {
        return service.deleteUser("Token $token")
    }
}