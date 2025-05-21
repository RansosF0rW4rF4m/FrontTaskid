package com.example.taskids.screens.child

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ChildRescopenseScreen(navController: NavController, childName: String = "NOME"){
    var showCompleted by remember { mutableStateOf(false) }

    val tasks = listOf("Arrumar a cama", "Estudar", "Guardar brinquedos")
    val completedTasks = listOf("Lavar as mãos", "Comer frutas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4DB8FF)) // azul claro
            .padding(16.dp)
    ) {
        // Topo: Foto + Nome + Troféu
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.LightGray, CircleShape)
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = childName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Icon(
                imageVector = Icons.Default.EmojiEvents,
                contentDescription = "Troféus",
                tint = Color(0xFFFFC107), // amarelo
                modifier = Modifier
                    .size(32.dp)
                    .clickable { navController.navigate("childhome") }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Botões: Tarefas e Completas!
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = { /* ação para tarefas */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Text("TESTE", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* ação para completas */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FF00)),
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
            ) {
                Text("TESTE!", color = Color.Black, fontWeight = FontWeight.Bold)
            }

        }
    }
}