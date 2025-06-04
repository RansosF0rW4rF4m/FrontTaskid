package com.example.taskids.data

import com.example.taskids.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UserService {

   @GET("api/users/users/{id}/")
    suspend fun getUserById(@Path("id") id: Int): Response<UserModel>

    @GET("api/users/users/")
    suspend fun getAllUsers(): List<UserModel>

    @PUT("api/users/users/{id}/")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UserModel
    ): Response<UserModel>

    @POST("api/users/users/")
    suspend fun createUser(@Body user: UserModel): Response<UserModel>

    @DELETE("api/users/users/{id}/")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>
}