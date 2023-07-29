package com.abdulmateen.startertemplate.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulmateen.startertemplate.presentation.screens.auth.login.LoginScreen
import com.abdulmateen.startertemplate.presentation.screens.auth.login.LoginViewModel
import com.abdulmateen.startertemplate.presentation.screens.auth.signup.SignUpScreen
import com.abdulmateen.startertemplate.presentation.screens.auth.signup.SignUpViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route =NavGraphs.AUTH_GRAPH,
        startDestination = ScreenRoute.Login.route
    ){
        composable(ScreenRoute.Login.route){
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents
            )
        }
        composable(ScreenRoute.SignUp.route){
            val viewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents
            )
        }
    }
}