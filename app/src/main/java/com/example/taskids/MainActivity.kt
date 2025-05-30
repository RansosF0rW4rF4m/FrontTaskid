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
import com.example.taskids.screens.AddTaskScreen
import com.example.taskids.screens.HomeScreen
import com.example.taskids.screens.User.EditUserProfileScreen
import com.example.taskids.screens.child.ChildRescopenseScreen
import com.example.taskids.screens.parent.TaskTabsScreen
import com.example.taskids.screens.child.ChildTaskScreen
import com.example.taskids.screens.child.QRCodeScannerScreen
import com.example.taskids.screens.parent.ParentLogin
import com.example.taskids.screens.parent.ParenRegister
import com.example.taskids.screens.parent.ParentChildQRCode
import com.example.taskids.screens.parent.ParentListChild
import com.example.taskids.screens.parent.ParentRegisterScreen
import com.example.taskids.screens.User.UserListScreen
import com.example.taskids.screens.User.UserRegistrationScreen
import com.example.taskids.screens.task.CreateTaskScreen
import com.example.taskids.screens.task.EditTaskScreen
import com.example.taskids.screens.task.TaskListScreen
import com.example.taskids.ui.theme.TaskidsTheme
import com.example.taskids.view.TaskViewModel
import com.example.taskids.view.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


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
                        composable ( route = "home" ) { HomeScreen(navController) }


//                        Lista de telas de Usuario:
                        composable ( route = "userlist" ) { UserListScreen (navController)}
                        composable ( route = "userRegister" ) { UserRegistrationScreen(navController)}
                        composable("editUser/{userId}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
                            val viewModel: UserViewModel = hiltViewModel() // ✅ Inject ViewModel

                            if (userId != null) {
                                EditUserProfileScreen(navController, viewModel, userId) // ✅ Pass userId properly
                            } else {
                                Text("❌ Error: User ID not found") // ✅ Prevent crashes
                            }
                        }


//                        Lista de telas de Task:
                        composable("taskList") { TaskListScreen(navController) }
                        composable("editTask/{taskId}") { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                            val viewModel: TaskViewModel = hiltViewModel()

                            if (taskId != null) {
                                EditTaskScreen(navController, viewModel, taskId) // ✅ Pass taskId properly
                            } else {
                                Text("❌ Error: Task ID not found")
                            }
                        }
                        composable("createTask") { CreateTaskScreen(navController) }




//                      pendente a integrar:

                        composable ( route = "parentlogin" ) { ParentLogin(navController) }
                        composable ( route = "parentregister" ) { ParenRegister(navController) }
                        composable ( route = "parentlistchild" ) { ParentListChild(navController) }
                        composable ( route = "parentregisterchild" ) { ParentRegisterScreen(navController) }
                        composable ( route = "parentchildqrcode" ) { ParentChildQRCode(navController) }
//                        composable ( route = "childlogin" ) { ChildLogin(navController) } -> Rota para habilitar o QrCode
                        composable ( route = "childhome") { ChildTaskScreen(navController) }
                        composable ( route = "childreconpense") { ChildRescopenseScreen(navController) }


//                        Não faço a minima ideia de como integrar já que ainda não temos uma view para atribuir um usurio a outro usuario:

                        composable("taskList/{childId}") { backStackEntry ->
                            val childId = backStackEntry.arguments?.getString("childId")?.toIntOrNull()
                            if (childId != null) {
                                TaskTabsScreen(childId = childId, navController = navController)
                            }
                        }

                        composable("addTask/{childId}") { backStackEntry ->
                            val childId = backStackEntry.arguments?.getString("childId")?.toIntOrNull() ?: return@composable
                            AddTaskScreen(childId = childId, navController = navController)
                        }

                        composable("scanqrcode") {
                            QRCodeScannerScreen { scannedCode ->
                                println("QR Code escaneado: $scannedCode")
                                // navController.navigate("taskscreen/${scannedCode}")
                            }
                            ///asdf
                        }
                    }
                }
            }
        }
    }
}
