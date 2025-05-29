package com.example.taskids.data

import com.example.taskids.models.UserModel

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: UserService
){
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
}