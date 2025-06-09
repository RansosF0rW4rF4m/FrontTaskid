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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


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

    val textFieldShape = RoundedCornerShape(8.dp)
    val shadowElevation = 8.dp

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(title = { Text("Registrar Usuário",fontSize = 28.sp, fontWeight = FontWeight.Bold,
                color = Color(0xFFE59900),) },
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
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                shadowElevation = 6.dp,
                shape = textFieldShape,
                color = Color.White
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nome do Usuário") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .shadow(
                            elevation = shadowElevation,
                            shape = textFieldShape,
                            clip = false
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        disabledLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }

            Surface(
                shadowElevation = 6.dp,
                shape = textFieldShape,
                color = Color.White
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .shadow(
                            elevation = shadowElevation,
                            shape = textFieldShape,
                            clip = false
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        disabledLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }

            Surface(
                shadowElevation = 6.dp,
                shape = textFieldShape,
                color = Color.White
            ) {
                TextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text("Biografia") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .shadow(
                            elevation = shadowElevation,
                            shape = textFieldShape,
                            clip = false
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        disabledLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Tipo de Usuário:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

// Row com os botões divididos meio a meio
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { userType = "guardian" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "guardian") Color(0xFFE8A319) else Color.Gray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)  // ocupa metade da linha
                ) {
                    Text("Responsável")
                }

                Button(
                    onClick = { userType = "kid" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "kid") Color(0xFFE8A319) else Color.Gray,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)  // ocupa metade da linha
                ) {
                    Text("Filho(a)")
                }
            }

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
                    containerColor = Color(0xFFE8A319),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(0.5f)) {
                Text("Cadastrar Usuário")
            }
        }
    }
}
