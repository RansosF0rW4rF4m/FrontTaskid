package com.example.taskids.data.network

import com.example.taskids.models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users/")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("users/{id}/")
    suspend fun getUserById(@Path("id") id: Int): Response<UserModel>

    @POST("users/")
    suspend fun createUser(@Body user: UserModel): Response<UserModel>

    @PUT("users/{id}/")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserModel): Response<UserModel>

    @DELETE("users/{id}/")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}