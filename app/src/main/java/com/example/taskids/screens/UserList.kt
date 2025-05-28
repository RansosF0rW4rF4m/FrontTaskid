package com.example.taskids.screens.parent

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskids.models.UserModel
import com.example.taskids.view.UserItem
import com.example.taskids.view.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onUserClick: (UserModel) -> Unit
) {
    val users = viewModel.users.value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Usuários") })
        }
    ) {
        LazyColumn {
            items(users) { user ->
                UserItem(user = user, onClick = onUserClick)
            }
        }
    }
}

