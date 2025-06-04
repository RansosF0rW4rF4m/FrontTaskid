package com.example.taskids.view
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskids.data.UserRepository
import com.example.taskids.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = mutableStateOf<List<UserModel>>(emptyList())
    val users: State<List<UserModel>> = _users

    private val _selectedUser = mutableStateOf<UserModel?>(null)
    val selectedUser: State<UserModel?> = _selectedUser


    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                _users.value = repository.getAllUsers()
            } catch (e: Exception) {
                println("❌ Erro ao buscar usuários: ${e.message}")
            }
        }
    }

    fun updateUser(id: Int, updatedUser: UserModel) {
        viewModelScope.launch {
            val success = repository.updateUser(id, updatedUser)
            if (success) {
                fetchUserById(id)
            } else {
                println("❌ Error updating user")
            }
        }
    }

    fun fetchUserById(id: Int){
        viewModelScope.launch {
            try {
                val user = repository.getUserById(id)
                _selectedUser.value = user
                println("User Stored in ViewModel: $user")
            } catch (e: Exception) {
                println("Erroe fetching user by ID: ${e.message}")
            }
        }
    }

    fun addUser(user: UserModel) {
        viewModelScope.launch {
            val success = repository.createUser(user)
            if (success) {
                fetchUsers()
            } else {
                println("Failed to create user")
            }
        }
    }

    fun deleteUser(id: Int){
        viewModelScope.launch {
            val sucess = repository.deleteUser(id)
            if (sucess) {
                fetchUsers()
            }
        }
    }
}
