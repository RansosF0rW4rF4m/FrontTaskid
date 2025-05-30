package com.example.taskids.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskids.models.UserModel


@Composable
fun UserItem(
    user: UserModel,
    onClick: (UserModel) -> Unit,
    onDeleteClick: (Int) -> Unit // ✅ Pass user ID for deletion
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(user) }, // ✅ Navigate to edit screen
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${user.first_name.orEmpty()} ${user.last_name.orEmpty()}".trim()
                        .ifEmpty { user.username ?: "Usuário sem nome" },
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black, fontSize = 16.sp)
                )
                if (!user.email.isNullOrEmpty()) {
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray, fontSize = 14.sp)
                    )
                }
            }

            Row {
                IconButton(onClick = { onDeleteClick(user.id) }) { // ✅ Calls delete function
                    Icon(Icons.Default.Delete, contentDescription = "Excluir", tint = Color.Red)
                }
            }
        }
    }
}
