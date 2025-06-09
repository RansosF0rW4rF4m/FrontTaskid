package com.example.taskids.screens.parent

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskids.view.UserItem
import com.example.taskids.view.UserViewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.snapshotFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val users = viewModel.users.value
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState() // ✅ Track scroll position

    var searchQuery by remember { mutableStateOf("") }
    var selectedUserType by remember { mutableStateOf("Todos") }

    val userTypeLabels = mapOf(
        "Todos" to "Todos",
        "Responsável" to "guardian",
        "Criança" to "kid"
    )

    // Filtro aplicando busca por nome/email e tipo
    val filteredUsers = users.filter { user ->
        val matchesQuery = searchQuery.isBlank() ||
                user.username?.contains(searchQuery, ignoreCase = true) == true ||
                (user.email?.contains(searchQuery, ignoreCase = true) == true)

        val matchesType = selectedUserType == "Todos" ||
                user.user_type.equals(selectedUserType, ignoreCase = true)

        matchesQuery && matchesType
    }

    // ✅ Fetch users when scrolling near the end of the list
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null && lastVisibleIndex >= users.size - 2) {
                    scope.launch { viewModel.fetchUsers() } // ✅ Fetch only when near bottom
                }
            }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de Usuários",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE59900),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("userRegister") },
                containerColor = Color(0xFFE59900),
                modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Adicionar Usuário",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // Campo único para busca por nome ou email
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Pesquisar por Nome ou Email") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = MaterialTheme.shapes.small,
                        clip = false
                    )
            )

            // Botões de filtro por tipo de usuário (com rótulos customizados)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                userTypeLabels.forEach { (label, value) ->
                    val selected = selectedUserType == value

                    Button(
                        onClick = { selectedUserType = value },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selected) Color(0xFFE8A319) else Color.LightGray,
                            contentColor = if (selected) Color.White else Color.Black
                        ),
                        border = if (selected) BorderStroke(2.dp, Color.White) else null,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = label,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }

                }
            }

            LazyColumn(
                state = listState, // ✅ Attach scroll tracking
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredUsers) { user ->
                    UserItem(
                        user = user,
                        onClick = { navController.navigate("editUser/${user.id}") },
                        onDeleteClick = { viewModel.deleteUser(user.id) }
                    )
                }
            }
        }
    }
}
