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
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskids.models.TaskModel

@Composable
fun TaskItem(
    task: TaskModel,
    onClick: (TaskModel) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(task) }, // ✅ Navigate to edit screen
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = if (task.completed) "✅ Concluída" else "⏳ Pendente",
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                )
            }

            Row {
                IconButton(onClick = { onDeleteClick(task.id) }) { // ✅ Calls delete function
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
