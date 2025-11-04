package com.abdulmateen.startertemplate.feature.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.feature.main.dashboard.DashboardScreen
import com.abdulmateen.startertemplate.feature.main.dashboard.home.product_detail.ProductDetailScreen

@Composable
fun MainNavGraph(
    navigateToAuth: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreenRoutes.Dashboard
    ) {
        composable<MainScreenRoutes.Dashboard>{
            DashboardScreen(
                onLogout = navigateToAuth,
                navigateToProductDetail = {
                    navController.navigate(MainScreenRoutes.ProductDetail(it))
                }
            )
        }
        composable<MainScreenRoutes.ProductDetail> {
            ProductDetailScreen()
        }
    }
}