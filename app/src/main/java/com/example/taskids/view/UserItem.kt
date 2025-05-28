package com.example.taskids.view
import android.R.attr.id
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = { onClick(user) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${user.firstName} ${user.lastName}",
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = when (user.userType) {
                    UserType.GUARDIAN -> "Responsável"
                    UserType.KID -> "Filho(a)"
                    null -> TODO()
                },
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}
