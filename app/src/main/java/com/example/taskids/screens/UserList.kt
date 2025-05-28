package com.example.taskids.screens.parent

import ChildItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.models.UserModel
import com.example.taskids.view.UserItem
import com.example.taskids.view.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onUserClick: (UserModel) -> Unit,
) {
    val users = viewModel.users.value

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(users) { user ->
            UserItem(user = user, onClick = onUserClick)
        }
    }
}

