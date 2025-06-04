package com.example.taskids.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.view.TaskItem
import com.example.taskids.view.TaskViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks = viewModel.tasks.value
    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }
    var showCompletedTasks by remember { mutableStateOf(false) }

    val filteredTasks = tasks.filter { task ->
        (task.title.contains(searchQuery, ignoreCase = true) ||
                task.description.contains(searchQuery, ignoreCase = true)) &&
                (!showCompletedTasks || task.completed)
    }

    Scaffold(
        containerColor = Color(0xFF2C2C6B),
        topBar = {
            TopAppBar(
                title = { Text("Lista de Tarefas") },
                actions = {
                    IconButton(onClick = { showCompletedTasks = !showCompletedTasks }) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = if (showCompletedTasks) "Mostrar Todas" else "Mostrar Concluídas",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = { scope.launch { viewModel.fetchTasks() } }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = "Atualizar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2C2C6B)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("createTask") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Tarefa")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Pesquisar Tarefa") },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                )
            }

            items(filteredTasks) { task ->
                TaskItem(
                    task = task,
                    onClick = { navController.navigate("editTask/${task.id}") },
                    onDeleteClick = { viewModel.deleteTask(task.id) }
                )
            }
        }
    }
}


