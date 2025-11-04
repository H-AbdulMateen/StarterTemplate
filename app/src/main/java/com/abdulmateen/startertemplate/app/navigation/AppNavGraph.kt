package com.abdulmateen.startertemplate.app.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.feature.auth.navigation.AuthNavGraph
import com.abdulmateen.startertemplate.feature.main.MainNavGraph

@Composable
fun AppNavGraph(
    isSignedIn: Boolean,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = if (isSignedIn) AppScreenRoutes.Main else AppScreenRoutes.AuthGraph
    ) {
        composable<AppScreenRoutes.AuthGraph>{
            AuthNavGraph(
                    navigateToMain = { navController.navigate(AppScreenRoutes.Main){
                        popUpTo(0){
                         inclusive = true
                        }
                    } }
            )
        }

        composable<AppScreenRoutes.Main>{
            MainNavGraph(
                navigateToAuth = { navController.navigate(AppScreenRoutes.AuthGraph){
                    popUpTo(0){
                        inclusive = true
                    }
                } }
            )
        }
    }
}
