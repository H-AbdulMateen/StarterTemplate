package com.lineztech.selfee.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lineztech.selfee.presentation.screens.main.MainScreen
import com.lineztech.selfee.presentation.screens.main.MainViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = NavGraphs.ROOT_GRAPH,
        startDestination = startDestination
    ){
        authNavGraph(navController = navController)

        composable(route = NavGraphs.MAIN_GRAPH){
            val viewModel: MainViewModel = hiltViewModel()
            MainScreen(
                logout = {
                    viewModel.logout()
                    navController.popBackStack()
                    navController.navigate(NavGraphs.AUTH_GRAPH)
                },
            )
        }
    }
}