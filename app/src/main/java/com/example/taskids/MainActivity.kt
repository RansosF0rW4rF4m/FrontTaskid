package com.example.taskids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskids.screens.HomeScreen
import com.example.taskids.screens.SelectionScreen
import com.example.taskids.screens.User.EditUserProfileScreen
import com.example.taskids.screens.User.UserRegistrationScreen
import com.example.taskids.screens.parent.ParentLogin
import com.example.taskids.screens.parent.ParentRegisterScreen
import com.example.taskids.screens.parent.UserListScreen
import com.example.taskids.screens.task.CreateTaskScreen
import com.example.taskids.screens.task.EditTaskScreen
import com.example.taskids.screens.task.TaskListScreen
import com.example.taskids.ui.theme.TaskidsTheme
import com.example.taskids.view.TaskViewModel
import com.example.taskids.view.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

//ijij

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskidsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF262466)
                ) {
                    val navController = rememberNavController()

                    NavHost(navController=navController, startDestination = "home") {
//                        Lista de tela de navegação inicial
                        composable ( route = "home" ) { HomeScreen(navController) }
                        composable (route = "parentlogin") { ParentLogin(navController) }
                        composable (route = "parentregister") { ParentRegisterScreen(navController) }
                        composable ( route = "selection" ) { SelectionScreen(navController) }

//                        Lista de telas de Usuario:
                        composable ( route = "userlist" ) { UserListScreen (navController)}
                        composable ( route = "userRegister" ) { UserRegistrationScreen(navController)}
                        composable("editUser/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
                            val viewModel: UserViewModel = hiltViewModel()

                            if (userId != null) {
                                EditUserProfileScreen(navController, viewModel, userId)
                            } else {
                                Text("❌ Error: User ID not found")
                            }
                        }


//                        Lista de telas de Task:
                        composable("taskList") { TaskListScreen(navController) }
                        composable("editTask/{taskId}") { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                            val viewModel: TaskViewModel = hiltViewModel()

                            if (taskId != null) {
                                EditTaskScreen(navController, viewModel, taskId)
                            } else {
                                Text("❌ Error: Task ID not found")
                            }
                        }
                        composable("createTask") { CreateTaskScreen(navController) }
                    }
                }
            }
        }
    }
}
