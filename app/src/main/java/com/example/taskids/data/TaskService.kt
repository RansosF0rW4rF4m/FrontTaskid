package com.example.taskids.data

import com.example.taskids.models.TaskModel
import retrofit2.Response
import retrofit2.http.*

interface TaskService {
    @GET("api/tasks/tasks/{id}/")
    suspend fun getTaskById(@Path("id") id: Int): Response<TaskModel>

    @GET("api/tasks/tasks/")
    suspend fun getAllTasks(): List<TaskModel>

    @POST("api/tasks/tasks/")
    suspend fun createTask(@Body task: TaskModel): Response<TaskModel>

    @PUT("api/tasks/tasks/{id}/")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body task: TaskModel
    ): Response<TaskModel>

    @DELETE("api/tasks/tasks/{id}/")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}
