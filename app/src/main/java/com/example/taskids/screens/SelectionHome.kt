package com.example.taskids.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.taskids.components.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Selecione abaixo se você quer cadastrar um usuario ou uma tarefa!", color = Color.White, modifier = Modifier.align(Alignment.Start))

        CustomButton(
            onClick = {navController.navigate("userlist")},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            label = "Usuario"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(
            onClick = {navController.navigate("taskList")},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            label = "Tarefa"
        )
    }
}

