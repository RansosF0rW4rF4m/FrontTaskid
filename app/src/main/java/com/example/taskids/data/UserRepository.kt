package com.example.taskids.data

import com.example.taskids.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
) {

    suspend fun getAllUsers(): List<UserModel> = service.getAllUsers()

    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            val response = service.loginUser(LoginRequest(email, password))
            if (response.isSuccessful) {
                val authData = response.body()
                println("✅ Login successful. Token: ${authData?.token}, User: ${authData?.username}")
                true
            } else {
                println("❌ Login failed: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("❌ Exception during login: ${e.message}")
            false
        }
    }

    suspend fun registerUser(email: String, password: String, userType: String, userType1: String): Boolean {
        return try {
            val response = service.registerUser(RegisterRequest(email, password, userType))
            if (response.isSuccessful) {
                println("✅ User registered successfully. Token: ${response.body()?.token}")
                true
            } else {
                println("❌ Registration failed: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            println("❌ Exception during registration: ${e.message}")
            false
        }
    }

    suspend fun createUser(user: UserModel): Boolean {
        return try {
            val response = service.createUser(user)
            response.isSuccessful
        } catch (e: Exception) {
            println("Error creating user: ${e.message}")
            false
        }
    }

    suspend fun getUserById(id: Int): UserModel? {
        return try {
            val response = service.getUserById(id)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            println("❌ Error fetching user: ${e.message}")
            null
        }
    }

    suspend fun deleteUser(id: Int): Boolean {
        return try {
            val response = service.deleteUser(id)
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

    suspend fun updateUser(id: Int, user: UserModel): Boolean {
        return try {
            val response = service.updateUser(id, user)
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

}
