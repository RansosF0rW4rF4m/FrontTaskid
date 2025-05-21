package com.example.taskids.screens.parent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TaskTabsScreen(childId: Int, navController: NavController) {
    val tabItems = listOf("Tarefas", "Histórico")
    var selectedTabIndex by remember { mutableStateOf(0) }

    val darkBlue = Color(0xFF2D2C72)
    val lightBlue = Color(0xFF4A90E2)
    val lightPurple = Color(0xFFA892D1)
    val bottomBackground = Color.White
    val fabBackground = Color(0xFFE5E5E5)

    Scaffold(
        containerColor = darkBlue,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fabiano ⌄",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        bottomBackground,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    tabItems.forEachIndexed { index, title ->
                        val isSelected = selectedTabIndex == index
                        val iconColor = if (isSelected) Color.White else Color.Black
                        val bgColor = if (isSelected) {
                            if (index == 0) lightBlue else lightPurple
                        } else Color.Transparent

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .clickable { selectedTabIndex = index }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(bgColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (index == 0) Icons.Default.Checklist else Icons.Default.History,
                                    contentDescription = title,
                                    tint = iconColor
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = title,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTask/$childId") },
                containerColor = fabBackground,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar tarefa", tint = darkBlue)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(darkBlue)
        ) {
            when (selectedTabIndex) {
                0 -> TaskList(childId)
                1 -> TaskHistory(childId)
            }
        }
    }
}

@Composable
fun TaskHistory(childId: Int) {

}

@Composable
fun TaskList(childId: Int) {

}
