package com.example.taskids.screens.parent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Usuários") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (users.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Carregando usuários...", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(users) { user ->
                        UserItem(user = user, onClick = onUserClick)
                    }
                }
            }
        }
    }
}

