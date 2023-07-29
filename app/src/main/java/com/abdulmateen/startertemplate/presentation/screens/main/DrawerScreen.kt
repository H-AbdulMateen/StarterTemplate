package com.abdulmateen.startertemplate.presentation.screens.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.RequestPage
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.domain.models.NavItemsModel
import com.abdulmateen.startertemplate.presentation.navigation.ScreenRoute
import kotlinx.coroutines.launch


@Composable
fun DrawerScreen(
    navController: NavController,
    closeDrawer: () -> Unit,
    logout: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerItems = listOf(
        NavItemsModel(route = ScreenRoute.Dashboard.route, label = stringResource(id = R.string.home), icon = Icons.Default.Home),
        NavItemsModel(route = ScreenRoute.MyBids.route, label = stringResource(id = R.string.my_bids), icon = Icons.Default.RequestPage),
        NavItemsModel(route = "Logout", label = stringResource(id = R.string.logout), icon = Icons.Default.Logout)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.primary,
        drawerContentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        drawerItems.forEach {item ->
            NavigationDrawerItem(
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (item.route == "Logout"){
                        scope.launch {
                            logout()
                        }
//                        navController.popBackStack()
//                        navController.navigate(NavGraphs.AUTH_GRAPH){
//                            popUpTo(navController.graph.findStartDestination().id){
//                                inclusive = true
//                            }
//                        }
                    }else{
                        navController.navigate(item.route){
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    closeDrawer()
                },
                shape = MaterialTheme.shapes.extraSmall,
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedBadgeColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }

    }

}

