package com.abdulmateen.startertemplate.feature.auth.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.feature.auth.presentation.login.LoginScreen
import com.abdulmateen.startertemplate.feature.auth.presentation.login.LoginViewModel
import com.abdulmateen.startertemplate.feature.auth.presentation.signup.SignUpScreen
import com.abdulmateen.startertemplate.feature.auth.presentation.signup.SignUpViewModel

@Composable
fun AuthNavGraph(
    navigateToMain: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthScreenRoutes.Login
    ) {
        composable<AuthScreenRoutes.Login>(
            exitTransition = { slideOutHorizontally() },
            popEnterTransition = { slideInHorizontally() }
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents,
                onLoginSuccess = {
                    navigateToMain()
                },
                navigateToSignUp = {
                    navController.navigate(AuthScreenRoutes.SignUp)
                },
                navigateToForgotPassword = {}
            )
        }

        composable<AuthScreenRoutes.SignUp>(
            enterTransition = {
                slideInHorizontally { initialOffset ->
                    initialOffset
                }
            },
            exitTransition = {
                slideOutHorizontally { initialOffset ->
                    initialOffset
                }
            }
        ) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            SignUpScreen(
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents,
                navigateToLogin = {
                    navController.navigateUp()
                }
            )
        }
    }
}