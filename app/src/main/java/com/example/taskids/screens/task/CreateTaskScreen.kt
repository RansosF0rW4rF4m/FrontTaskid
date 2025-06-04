package com.example.taskids.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.models.TaskModel
import com.example.taskids.view.TaskViewModel
import com.example.taskids.view.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var completed by remember { mutableStateOf(false) }
    var points by remember { mutableStateOf("") }
    var assigned_to by remember { mutableStateOf<Int?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val userViewModel: UserViewModel = hiltViewModel()
    val users = userViewModel.users.value.filter { it.user_type == "kid" }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Criar Nova Tarefa") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val pointsInt = points.toIntOrNull() ?: 10

                val newTask = TaskModel(
                    id = 0,
                    title = title,
                    description = description,
                    assigned_to = assigned_to,
                    completed = completed,
                    points = pointsInt
                )
                viewModel.createTask(newTask)
                navController.popBackStack()
            }) {
                Icon(Icons.Default.Check, contentDescription = "Criar")
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
            TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") })
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = completed, onCheckedChange = { completed = it })
                Text("Concluída")
            }
            TextField(
                value = points,
                onValueChange = { points = it },
                label = { Text("Pontos") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            Text("Atribuir a:")
            Box {
                Button(onClick = { expanded = true }) {
                    Text(if (assigned_to == null) "Selecionar Criança" else "Usuário ID: ${assigned_to}")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    users.forEach { user ->
                        DropdownMenuItem(
                            text = { Text(user.username.toString()) },
                            onClick = {
                                assigned_to = user.id
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
