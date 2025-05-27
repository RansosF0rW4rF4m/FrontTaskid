package com.example.taskids.data

import com.example.taskids.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    // Login simplificado (sem token)
    @POST("users/login/")
    suspend fun login(@Body credentials: Map<String, String>): Response<UserModel>

    // Registro (sem alteração necessária)
    @POST("users/register/")
    suspend fun register(@Body user: UserModel): Response<UserModel>

    // Obter perfil por ID (em vez de token)
    @GET("users/{id}/")
    suspend fun getUser(@Path("id") id: Int): Response<UserModel>

    // Atualizar perfil por ID
    @PUT("users/{id}/")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body user: UserModel
    ): Response<UserModel>

    // Deletar usuário por ID
    @DELETE("users/{id}/")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}