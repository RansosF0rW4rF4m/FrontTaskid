package com.example.taskids.screens.parent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskids.components.CustomButton
import com.example.taskids.components.MyTextField

@Composable
fun ParentRegisterScreen(navController: NavController){
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MyTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            label = "Nome",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        MyTextField(
            value = age,
            onValueChange = { age = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp, 20.dp, 0.dp),
            label = "Idade",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(24.dp))

        CustomButton(
            onClick = {navController.navigate("parentchildqrcode")},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp),
            label = "CADASTRAR"
        )
    }
}