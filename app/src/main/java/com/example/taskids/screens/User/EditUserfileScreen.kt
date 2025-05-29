package com.example.taskids.screens.User

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.view.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserProfileScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
    userId: Int
) {
    val user by viewModel.selectedUser

    LaunchedEffect(userId) {
        viewModel.fetchUserById(userId)
    }

    user?.let { userData ->
        var username by remember { mutableStateOf(userData.username ?: "") }
        var email by remember { mutableStateOf(userData.email ?: "") }

        Scaffold(
            topBar = { TopAppBar(title = { Text("Editar Perfil") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val updatedUser = userData.copy(username = username, email = email)
                    viewModel.updateUser(userId, updatedUser)
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.Check, contentDescription = "Salvar")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(value = username, onValueChange = { username = it }, label = { Text("Nome") })
                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            }
        }
    } ?: run {
        Text("Carregando usuário...")
    }
}

