package com.example.taskids.data

import com.example.taskids.models.UserModel

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
) {
    // Usa diretamente o userService já configurado no ApiClient
//    suspend fun login(username: String, password: String): UserModel {
//        return service.login(mapOf("username" to username, "password" to password))
//    }
//
//    suspend fun register(user: UserModel): UserModel {
//        return service.register(user)
//    }
//
//    suspend fun getUser(id: Int): UserModel {
//        return service.getUser(id)
//    }

    suspend fun getAllUsers(): List<UserModel> = service.getAllUsers()

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
            val response = service.getUserById(id) // API request
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            println("❌ Error fetching user: ${e.message}")
            null
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

//    suspend fun GetChildren(): List<UserModel> {
//        return getAllUsers().filter { it.userType == UserType.KID }
//    }
//
//    suspend fun GetParents(): List<UserModel> {
//        return getAllUsers().filter { it.userType == UserType.GUARDIAN }
//    }

fun getParentsOfChild(child: UserModel, users: List<UserModel>): List<UserModel> {
    return users.filter { it.id in child.guardians }
}

fun getChildrenOfParent(parent: UserModel, users: List<UserModel>): List<UserModel> {
    return users.filter { it.id in parent.kids }
}

//    suspend fun updateUser(id: Int, user: UserModel): UserModel {
//        return service.updateProfile(id, user)
//    }
//
//    suspend fun deleteUser(id: Int): UserModel {
//        return service.deleteUser(id)
//    }