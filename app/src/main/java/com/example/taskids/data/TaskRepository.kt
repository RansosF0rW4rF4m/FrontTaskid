package com.example.taskids.data

import com.example.taskids.models.TaskModel
import javax.inject.Inject


class TaskRepository @Inject constructor(
    private val service: TaskService
) {
    suspend fun getTaskById(id: Int): TaskModel? {
        return try {
            val response = service.getTaskById(id)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            println("❌ Error fetching task: ${e.message}")
            null
        }
    }

    suspend fun getAllTasks(): List<TaskModel> = service.getAllTasks()

    suspend fun createTask(task: TaskModel): Boolean {
        return try {
            val response = service.createTask(task)
            response.isSuccessful
        } catch (e: Exception) {
            println("❌ Error creating task: ${e.message}")
            false
        }
    }

    suspend fun updateTask(id: Int, task: TaskModel): Boolean {
        return try {
            val response = service.updateTask(id, task)
            if (response.isSuccessful) {
                println("User updated successfully")
                true
            } else {
                println(" Error updating user: ${response.errorBody()}")
                false
            }
        } catch (e: Exception) {
            println(" Exception updating user: ${e.message}")
            false
        }
    }

    suspend fun deleteTask(id: Int): Boolean {
        return try {
            val response = service.deleteTask(id)
            if (response.isSuccessful) {
                println("✅ User deleted successfully")
                true
            } else {
                println("❌ Error deleting user: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("❌ Exception deleting user: ${e.message}")
            false
        }
    }
}
