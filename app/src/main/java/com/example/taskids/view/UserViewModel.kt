package com.example.taskids.view

import android.R.attr.id
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskids.data.UserRepository
import com.example.taskids.models.UserModel
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    val users = MutableLiveData<List<UserModel>>()
    val user = MutableLiveData<UserModel?>()

    fun loadUsers() {
        viewModelScope.launch {
            val response = repository.getUser(id)
            if (response.isSuccessful) {
                users.postValue(mutableListOf())
            }
        }
    }
}