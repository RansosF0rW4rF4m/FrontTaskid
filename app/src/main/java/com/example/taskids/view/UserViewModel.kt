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

    private val _loginSuccess = mutableStateOf<Boolean?>(null)  // ✅ Track login state
    val loginSuccess: State<Boolean?> = _loginSuccess

    private val _registrationSuccess = mutableStateOf<Boolean?>(null)  // ✅ Track registration state
    val registrationSuccess: State<Boolean?> = _registrationSuccess


    init {
        fetchUsers()
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val success = repository.loginUser(username, password)
            _loginSuccess.value = success // ✅ Update login state
            if (success) {
                println("✅ Login successful!")
            } else {
                println("❌ Invalid credentials.")
            }
        }
    }

    fun clearLoginState() {
        _loginSuccess.value = null
    }

    fun clearRegistrationState() {
        _registrationSuccess.value = null
    }




    fun registerUser(username: String, password: String, userType: String) {
        viewModelScope.launch {
            val success = repository.registerUser(username, password, userType)
            _registrationSuccess.value = success // ✅ Update registration state
            if (success) {
                println("✅ Registration complete!")
            } else {
                println("❌ Registration failed.")
            }
        }
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

    companion object
}
