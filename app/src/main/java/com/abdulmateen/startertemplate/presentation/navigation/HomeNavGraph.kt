package com.abdulmateen.startertemplate.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abdulmateen.startertemplate.presentation.screens.main.dashboard.home.HomeScreen
import com.abdulmateen.startertemplate.presentation.screens.main.dashboard.home.HomeViewModel
import com.abdulmateen.startertemplate.presentation.screens.main.requests.RequestsScreen
import com.abdulmateen.startertemplate.presentation.screens.main.profile.ProfileScreen
import com.abdulmateen.startertemplate.presentation.screens.main.profile.ProfileViewModel
import com.abdulmateen.startertemplate.presentation.screens.main.requests.RequestsViewModel

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {

    NavHost(
        startDestination = ScreenRoute.Home.route,
        navController = navController
    ){
        composable(
            route = ScreenRoute.Home.route,
        ){
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onEvent,
                apiEvents = viewModel.apiEvents
            )
        }

        composable(
            route = ScreenRoute.Profile.route
        ){
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents =  viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents
            )
        }

        composable(
            route = ScreenRoute.Requests.route
        ){
            val viewModel: RequestsViewModel = hiltViewModel()
            RequestsScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents
            )
        }
    }
}