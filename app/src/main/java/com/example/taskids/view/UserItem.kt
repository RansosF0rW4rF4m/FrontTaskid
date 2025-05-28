package com.example.taskids.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskids.models.UserModel
import com.example.taskids.models.UserType

@Composable
fun UserItem(
    user: UserModel,
    onClick: (UserModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(user) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}".trim()
                        .ifEmpty { user.username ?: "Usuário sem nome" },
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )
                if (!user.email.isNullOrEmpty()) {
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    )
                }
            }

            Text(
                text = when (user.userType) {
                    UserType.GUARDIAN -> "Responsável"
                    UserType.KID -> "Filho(a)"
                    null -> "Tipo indefinido"
                },
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )
        }
    }
}
