package com.lineztech.selfee.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.lineztech.selfee.data.AppDataStore
import com.lineztech.selfee.presentation.navigation.NavGraphs
import com.lineztech.selfee.presentation.navigation.RootNavGraph
import com.lineztech.selfee.presentation.ui.theme.SelfeeTheme
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