package com.example.taskids.data.network

import com.example.taskids.models.TaskModel
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @GET("tasks/")
    suspend fun getTasks(@Header("Authorization") token: String): Response<List<TaskModel>>

    @POST("tasks/")
    suspend fun createTask(
        @Header("Authorization") token: String,
        @Body task: TaskModel
    ): Response<TaskModel>

    @PUT("tasks/{id}/")
    suspend fun updateTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body task: TaskModel
    ): Response<TaskModel>

    @DELETE("tasks/{id}/")
    suspend fun deleteTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Unit>

    @Multipart
    @POST("tasks/{id}/upload-before/")
    suspend fun uploadBeforeImage(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Part image: MultipartBody.Part
    ): Response<TaskModel>

    @Multipart
    @POST("tasks/{id}/upload-after/")
    suspend fun uploadAfterImage(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Part image: MultipartBody.Part
    ): Response<TaskModel>

    @POST("tasks/{id}/validate/")
    suspend fun validateTask(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<TaskModel>
}