package com.example.taskids.screens.parent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import com.example.taskids.components.CustomButton
import com.example.taskids.components.MyTextField
import com.example.taskids.components.MyTextFieldPassword

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentLogin(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo",
            color = Color(0xFFE59900),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Entre e mantenha o controle",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(32.dp))

        MyTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            label = "Digite seu email",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(16.dp))

        MyTextFieldPassword(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            label = "Digite sua senha",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            onClick = {navController.navigate("selection")},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            label = "Entrar"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "não possui conta? ",
            color = Color(0xFFE59900),
            fontSize = 14.sp
        )

        Text(
            text = "clique aqui",
            color = Color(0xFFE59900),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    navController.navigate("parentregister")
                }
        )
    }
}

