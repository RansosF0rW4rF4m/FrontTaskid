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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.text.font.FontWeight
import com.example.taskids.components.CustomButton
import com.example.taskids.components.MyTextField
import com.example.taskids.components.MyTextFieldPassword
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.example.taskids.view.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentLogin(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginMessage by remember { mutableStateOf("") } // ✅ Store feedback message
    val scope = rememberCoroutineScope()
    val userViewModel: UserViewModel = hiltViewModel() // ✅ Inject ViewModel

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

        Spacer(modifier = Modifier.height(32.dp))

        MyTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            label = "email",
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
            label = "password",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            onClick = {
                userViewModel.loginUser(username, password) // ✅ Call login API
            },
            modifier = Modifier.fillMaxWidth().height(80.dp).padding(10.dp),
            label = "Entrar"
        )

        Spacer(modifier = Modifier.height(8.dp))

        LaunchedEffect(userViewModel.loginSuccess.value) {
            userViewModel.loginSuccess.value?.let { success ->
                if (success) {
                    navController.navigate("selection") // ✅ Navigate when login succeeds
                } else {
                    loginMessage = "❌ Credenciais inválidas. Tente novamente!"
                }
            }
        }

        Text(
            text = loginMessage,
            color = if (loginMessage.startsWith("✅")) Color.Green else Color.Red,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Não possui conta?",
            color = Color(0xFFE59900),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "CLICK AQUI",
            color = Color.Blue,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("parentregister")
            }
        )
    }
}