package com.abdulmateen.startertemplate.presentation.screens.main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.presentation.navigation.MainNavGraph
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController = rememberNavController(),
    logout: () -> Unit = {}
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            DrawerScreen(
                navController = navController,
                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                },
                logout = logout
            )
        },
        drawerState = drawerState
    ) {
        MainNavGraph(
            navController = navController as NavHostController,
            drawerState = drawerState
        )
    }
}

