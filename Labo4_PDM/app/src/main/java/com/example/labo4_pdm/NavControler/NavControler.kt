package com.example.labo4_pdm.NavController

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labo4_pdm.ui.Screens.CreateTaskScreen.CreateTaskScreen
import com.example.labo4_pdm.ui.Screens.TODOScreen.TODOScreen
import com.example.labo4_pdm.viewModel.GeneralViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val generalViewModel: GeneralViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable(route = "todo_list") {
            TODOScreen(
                viewModel = generalViewModel,
                onNavigateToCreate = { navController.navigate("create_task") }
            )
        }
        composable(route = "create_task") {
            CreateTaskScreen(
                viewModel = generalViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}