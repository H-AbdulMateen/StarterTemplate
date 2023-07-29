package com.lineztech.selfee.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lineztech.selfee.presentation.screens.main.dashboard.DashboardScreen
import com.lineztech.selfee.presentation.screens.main.my_bids.MyBidsScreen
import com.lineztech.selfee.presentation.screens.main.requests.RequestsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    NavHost(
        startDestination = ScreenRoute.Dashboard.route,
        navController = navController
    ){
        composable(
            route = ScreenRoute.Dashboard.route,
        ){
            DashboardScreen(
                navController = navController,
                drawerState = drawerState
            )
        }

        composable(
            route = ScreenRoute.MyBids.route
        ){
            MyBidsScreen(navController = navController)
        }
    }
}