package com.example.taskids.data

import com.example.taskids.data.network.ApiService
import com.example.taskids.models.UserModel
import retrofit2.Response

class UserRepository {
    // Usa diretamente o userService já configurado no ApiClient
    private val service = ApiService.userService

    suspend fun login(username: String, password: String): Response<UserModel> {
        return service.login(mapOf("username" to username, "password" to password))
    }

    suspend fun register(user: UserModel): Response<UserModel> {
        return service.register(user)
    }

    suspend fun getUser(id: Int): Response<UserModel> {
        return service.getUser(id)
    }

    suspend fun updateUser(id: Int, user: UserModel): Response<UserModel> {
        return service.updateProfile(id, user)
    }

    suspend fun deleteUser(id: Int): Response<Unit> {
        return service.deleteUser(id)
    }
}