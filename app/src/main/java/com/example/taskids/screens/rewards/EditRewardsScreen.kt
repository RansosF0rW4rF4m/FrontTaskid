package com.example.taskids.screens.rewards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.view.RewardsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRewardScreen(
    navController: NavController,
    viewModel: RewardsViewModel = hiltViewModel(),
    rewardId: Int
) {
    val reward by viewModel.selectedReward

    LaunchedEffect(rewardId) {
        viewModel.fetchRewardById(rewardId)
    }

    reward?.let { rewardData ->
        var title by remember { mutableStateOf(rewardData.title) }
        var pointsRequired by remember { mutableStateOf(rewardData.points_required.toString()) }

        Scaffold(
            containerColor = Color.White,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Editar Recompensa",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE59900)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        val updatedReward = rewardData.copy(
                            title = title,
                            points_required = pointsRequired.toIntOrNull() ?: rewardData.points_required
                        )
                        viewModel.updateReward(rewardId, updatedReward)
                        navController.popBackStack()
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
                    .padding(horizontal = 16.dp)
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título da Recompensa") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .shadow(8.dp, MaterialTheme.shapes.small),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                TextField(
                    value = pointsRequired,
                    onValueChange = { pointsRequired = it },
                    label = { Text("Pontos Necessários") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .shadow(8.dp, MaterialTheme.shapes.small),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    } ?: run {
        Text("Carregando recompensa...")
    }
}
