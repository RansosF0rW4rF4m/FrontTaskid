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
import com.example.taskids.models.RewardsModel

@Composable
fun RewardItem(
    reward: RewardsModel,
    onClick: (RewardsModel) -> Unit,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(reward) },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = reward.title,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                )
                Text(
                    text = "${reward.points_required} pontos necessários",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }

            IconButton(onClick = { onDeleteClick(reward.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir Recompensa",
                    tint = Color.Red
                )
            }
        }
    }
}
