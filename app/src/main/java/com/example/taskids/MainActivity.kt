package com.example.taskids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskids.screens.AddTaskScreen
import com.example.taskids.screens.HomeScreen
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
import com.example.taskids.ui.theme.TaskidsTheme
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
                    color = Color(0xFF262466) // fundo azul escuro
                ) {
                    val navController = rememberNavController()

                    NavHost(navController=navController, startDestination = "userlist") {
                        composable ( route = "home" ) { HomeScreen(navController) }

                        composable ( route = "userlist" ) { UserListScreen (navController) {user -> navController.navigate("userDetail/${user.id}")} }
                        composable ( route = "userRegister" ) { UserRegistrationScreen(navController)}


                        composable ( route = "parentlogin" ) { ParentLogin(navController) }
                        composable ( route = "parentregister" ) { ParenRegister(navController) }
                        composable ( route = "parentlistchild" ) { ParentListChild(navController) }
                        composable ( route = "parentregisterchild" ) { ParentRegisterScreen(navController) }
                        composable ( route = "parentchildqrcode" ) { ParentChildQRCode(navController) }
//                        composable ( route = "childlogin" ) { ChildLogin(navController) } -> Rota para habilitar o QrCode
                        composable ( route = "childhome") { ChildTaskScreen(navController) }
                        composable ( route = "childreconpense") { ChildRescopenseScreen(navController) }

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
