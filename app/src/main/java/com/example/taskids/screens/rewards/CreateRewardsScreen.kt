package com.example.taskids.screens.rewards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController import com.example.taskids.models.RewardsModel
import com.example.taskids.view.RewardsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRewardScreen(
    navController: NavController,
    viewModel: RewardsViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var points by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val textFieldShape = MaterialTheme.shapes.small
    val shadowElevation = 8.dp

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Criar Recompensa",
                        fontSize = 28.sp,
                        color = Color(0xFFE59900)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val pointsInt = points.toIntOrNull()

                    if (title.isBlank() || pointsInt == null) {
                        errorMessage = "❌ Preencha todos os campos corretamente."
                    } else {
                        val newReward = RewardsModel(
                            id = 0,
                            title = title,
                            points_required = pointsInt
                        )
                        viewModel.createReward(newReward)
                        navController.popBackStack()
                    }
                },
                containerColor = Color(0xFFE59900),
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(Icons.Default.Check, contentDescription = "Salvar", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Adicionar Recompensa",
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título da Recompensa") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .shadow(shadowElevation, textFieldShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = points,
                onValueChange = { points = it },
                label = { Text("Pontos Necessários") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .shadow(shadowElevation, textFieldShape),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )

            if (errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
        }
    }
}
