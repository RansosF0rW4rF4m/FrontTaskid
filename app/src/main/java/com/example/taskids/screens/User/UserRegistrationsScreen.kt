package com.example.taskids.screens.User

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.models.UserModel
import com.example.taskids.view.UserViewModel
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegistrationScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var first_name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("guardian") } // Default is guardian

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(title = { Text("Registrar Usuário", color = Color.Black) },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nome do Usuário") },
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            TextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Biografia") },
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            Row {
                Text("Tipo de Usuário:")
                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { userType = "guardian" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "guardian") MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text("Responsável")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { userType = "kid" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "kid") MaterialTheme.colorScheme.primary else Color.Gray
                    )
                ) {
                    Text("Filho(a)")
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    val newUser = UserModel(
                        id = 0,
                        username = username,
                        email = email,
                        first_name = null,
                        last_name = null,
                        user_type = userType,
                        bio = null,
                        kids = emptyList(),
                        guardians = emptyList()
                    )
                    viewModel.addUser(newUser)
                    navController.popBackStack() // Return to previous screen
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE59900),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.5f)) {
                Text("Cadastrar Usuário")
            }
        }
    }
}
