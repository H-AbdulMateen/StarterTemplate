package com.abdulmateen.startertemplate.feature.main.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.feature.main.dashboard.home.product_list.HomeScreen
import com.abdulmateen.startertemplate.feature.main.dashboard.home.product_list.HomeViewModel
import com.abdulmateen.startertemplate.feature.main.dashboard.profile.ProfileScreen
import com.abdulmateen.startertemplate.feature.main.dashboard.profile.ProfileViewModel
import com.abdulmateen.startertemplate.feature.main.dashboard.settings.SettingsScreen


@Composable
fun DashboardScreen(
    onLogout: () -> Unit,
    navigateToProductDetail: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                hierarchy = navController.currentBackStackEntryAsState().value?.destination?.hierarchy,
                navController = navController
            )
        }
    ) {innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()){
            NavHostPane(
                navController = navController,
                onLogoutClick = { onLogout() },
                navigateToProductDetail = {
                }
            )
        }
    }
}


@Composable
fun NavHostPane(
    navigateToProductDetail: (Int) -> Unit,
    onLogoutClick: () -> Unit,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = DashboardScreenRoutes.Home,

        ) {
        composable<DashboardScreenRoutes.Home>() {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                navigateToProductDetail = { navigateToProductDetail(it) },
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onEvent,
                apiEvents = viewModel.apiEvents
            )
        }
        composable<DashboardScreenRoutes.Profile>() {
            val viewModel = hiltViewModel<ProfileViewModel>()
            ProfileScreen(
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent,
                apiEvents = viewModel.apiEvents
            )
        }
        composable<DashboardScreenRoutes.Settings>() {
            SettingsScreen(
                onLogout = onLogoutClick
            )
        }

    }
}


@Composable
fun BottomNavBar(
    hierarchy: Sequence<NavDestination>?,
    navController: NavController
) {
    NavigationBar {
        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(DashboardScreenRoutes.Home::class) } == true,
            icon = { Icon(imageVector = Icons.Default.Home, "emails") },
            onClick = { navController.navigate(DashboardScreenRoutes.Home) }
        )
        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(DashboardScreenRoutes.Profile::class) } == true,
            icon = { Icon(imageVector = Icons.Default.Person, "profile") },
            onClick = { navController.navigate(DashboardScreenRoutes.Profile) }
        )
        NavigationBarItem(
            selected = hierarchy?.any { it.hasRoute(DashboardScreenRoutes.Settings::class) } == true,
            icon = { Icon(imageVector = Icons.Default.Settings, "settings") },
            onClick = { navController.navigate(DashboardScreenRoutes.Settings) }
        )
    }
}


@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        onLogout = {},
        navigateToProductDetail = {}
    )
}