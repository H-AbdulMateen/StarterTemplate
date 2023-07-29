package com.abdulmateen.startertemplate.presentation.screens.main.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.components.TopBarWithDrawer
import com.abdulmateen.startertemplate.presentation.navigation.HomeNavGraph
import com.abdulmateen.startertemplate.presentation.navigation.ScreenRoute
import com.abdulmateen.startertemplate.domain.models.NavItemsModel
import kotlinx.coroutines.launch


@Composable
fun DashboardScreen(
    navController: NavController,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val scope = rememberCoroutineScope()
    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = {
            TopBarWithDrawer {
                scope.launch {
                    drawerState.open()
                }
            }
        },
        bottomBar = {
            BottomBarContent(navController = bottomNavController)
        }
    ) {innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()){
            HomeNavGraph(navController = bottomNavController)
        }
    }
}

@Composable
fun BottomBarContent(navController: NavController) {
    val items = listOf(
        NavItemsModel(route = ScreenRoute.Home.route, label = stringResource(id = R.string.home), icon = Icons.Default.Home),
        NavItemsModel(route = ScreenRoute.Profile.route, label = stringResource(id = R.string.profile), icon = Icons.Default.Person),
        NavItemsModel(route = ScreenRoute.Requests.route, label = stringResource(id = R.string.requests), icon = Icons.Default.Description)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        items.forEach {item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route){
                    navController.graph.startDestinationRoute?.let { screen_route ->
                        popUpTo(screen_route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                } },
                icon = { Icon(imageVector = item.icon, contentDescription = "Icon") },
                label = { Text(text = item.label) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

