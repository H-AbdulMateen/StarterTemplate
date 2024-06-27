package com.abdulmateen.startertemplate.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.data.AppDataStore
import com.abdulmateen.startertemplate.presentation.navigation.NavGraphs
import com.abdulmateen.startertemplate.presentation.navigation.RootNavGraph
import com.abdulmateen.startertemplate.presentation.navigation.ScreenRoute
import com.abdulmateen.startertemplate.presentation.screens.main.MainViewModel
import com.abdulmateen.startertemplate.presentation.ui.theme.SelfeeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var dataStore: AppDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition {
                    return@setKeepOnScreenCondition viewModel.isLoading.value
                }
            }
        setContent {
            SelfeeTheme {
                val loginRoute = ScreenRoute.Login.route
                RootNavGraph(
                    navController = rememberNavController(),
                    startDestination = if (viewModel.startDestination.value == loginRoute) {
                        NavGraphs.AUTH_GRAPH
                    } else {
                        NavGraphs.MAIN_GRAPH
                    }
                )
            }
        }

    }
}