package com.example.taskids.data

import com.example.taskids.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    // Login simplificado (sem token)
    @POST("api/users/login/")
    suspend fun login(@Body credentials: Map<String, String>): UserModel

    // Registro (sem alteração necessária)
    @POST("api/users/register/")
    suspend fun register(@Body user: UserModel): UserModel

    // Obter perfil por ID (em vez de token)
    @GET("api/users/users/{id}/")
    suspend fun getUserById(@Path("id") id: Int): Response<UserModel>

    @GET("api/users/users/")
    suspend fun getAllUsers(): List<UserModel>

    @POST("api/users/users/")
    suspend fun createUser(@Body user: UserModel): Response<UserModel>

    // Deletar usuário por ID
    @DELETE("api/users/users/{id}/")
    suspend fun deleteUser(@Path("id") id: Int): UserModel
}