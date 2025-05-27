package com.example.taskids.data.repository

import com.example.taskids.data.network.ApiClient
import com.example.taskids.data.network.TaskService
import com.example.taskids.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TaskRepository{

    private val service = ApiClient.retrofit.create(TaskService::class.java)

    suspend fun getTask(id: Int): TaskModel? = withContext(Dispatchers.IO) {
        val response = service.getTasks(id)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun createTask(task: TaskModel): TaskModel? = withContext(Dispatchers.IO) {
        val response = service.createTask(task)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun updateTask(id: Int, task: TaskModel): TaskModel? = withContext(Dispatchers.IO) {
        val response = service.updateTask(id, task)
        if (response.isSuccessful) response.body() else null
    }

    suspend fun deleteTask(id: Int): Boolean = withContext(Dispatchers.IO) {
        service.deleteTask(id).isSuccessful
    }
}