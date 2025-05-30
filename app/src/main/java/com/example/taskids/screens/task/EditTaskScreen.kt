package com.example.taskids.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.view.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
    taskId: Int
) {
    val task by viewModel.selectedTask

    LaunchedEffect(taskId) {
        viewModel.fetchTaskById(taskId) // ✅ Fetch task data on screen open
    }

    task?.let { taskData ->
        var title by remember { mutableStateOf(taskData.title) }
        var description by remember { mutableStateOf(taskData.description) }
        var completed by remember { mutableStateOf(taskData.completed) }

        Scaffold(
            topBar = { TopAppBar(title = { Text("Editar Tarefa") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val updatedTask = taskData.copy(title = title, description = description, completed = completed)
                    viewModel.updateTask(taskId, updatedTask) // ✅ Call update function
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
                TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") })
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = completed, onCheckedChange = { completed = it })
                    Text("Concluída")
                }
            }
        }
    } ?: run {
        Text("Carregando tarefa...") // ✅ Display loading state if task isn't available yet
    }
}
