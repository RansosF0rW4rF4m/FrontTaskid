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

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                println("🔄 Buscando usuários...")
                val result = repository.getAllUsers()
                _users.value = result
                println("✅ Usuários carregados: ${result.size}")
            } catch (e: Exception) {
                println("❌ Erro ao buscar usuários: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    // Opcional: para adicionar um usuário manualmente
    fun addUser(user: UserModel) {
        _users.value = _users.value + user
    }
}
