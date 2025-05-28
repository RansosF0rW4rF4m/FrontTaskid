package com.example.taskids.data

import com.example.taskids.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    // Login simplificado (sem token)
    @POST("users/login/")
    suspend fun login(@Body credentials: Map<String, String>): UserModel

    // Registro (sem alteração necessária)
    @POST("users/register/")
    suspend fun register(@Body user: UserModel): UserModel

    // Obter perfil por ID (em vez de token)
    @GET("users/{id}/")
    suspend fun getUser(@Path("id") id: Int): UserModel

    @GET("users/users/")
    suspend fun getAllUsers(): UserModel

    // Atualizar perfil por ID
    @PUT("users/{id}/")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body user: UserModel
    ): UserModel

    // Deletar usuário por ID
    @DELETE("users/{id}/")
    suspend fun deleteUser(@Path("id") id: Int): UserModel
}