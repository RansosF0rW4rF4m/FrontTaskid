package com.example.taskids.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskids.models.TaskModel

@Composable
fun TaskItem(
    task: TaskModel,
    onClick: (TaskModel) -> Unit,
    onDeleteClick: (Int) -> Unit,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val assignedUser = userViewModel.users.value.find { it.id == task.assigned_to }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(task) },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                )
                Text(
                    text = assignedUser?.username ?: "Usuário desconhecido",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Text(
                    text = if (task.completed) "✅ Concluída" else "⏳ Pendente",
                    style = MaterialTheme.typography.labelMedium.copy(color = if (task.completed) Color(0xFF34A853) else Color(0xFFE37400)),
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${task.points} pts",
                    style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF5B8DF6))
                )
                IconButton(onClick = { onDeleteClick(task.id) }) {
                    androidx.compose.material3.Icon(
                        Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
