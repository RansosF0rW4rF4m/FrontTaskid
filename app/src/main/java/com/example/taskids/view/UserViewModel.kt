package com.example.taskids.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskids.data.repository.UserRepository
import com.example.taskids.models.UserModel
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    val users = MutableLiveData<List<UserModel>>()
    val user = MutableLiveData<UserModel?>()

    fun loadUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            if (response.isSuccessful) {
                users.postValue(response.body())
            }
        }
    }

    fun loadUserById(id: Int) {
        viewModelScope.launch {
            val response = repository.getUserById(id)
            if (response.isSuccessful) {
                user.postValue(response.body())
            }
        }
    }
}