package com.abdulmateen.startertemplate.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.data.AppDataStore
import com.abdulmateen.startertemplate.presentation.navigation.NavGraphs
import com.abdulmateen.startertemplate.presentation.navigation.RootNavGraph
import com.abdulmateen.startertemplate.presentation.ui.theme.SelfeeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: AppDataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SelfeeTheme {
                val isLoggedIn = dataStore.isLoggedIn.value
                RootNavGraph(
                    navController = rememberNavController(),
                    startDestination = if (!isLoggedIn){
                        NavGraphs.AUTH_GRAPH
                    }else{
                        NavGraphs.MAIN_GRAPH
                    }
                )
            }
        }

    }
}