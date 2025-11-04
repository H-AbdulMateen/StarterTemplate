package com.abdulmateen.startertemplate.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abdulmateen.startertemplate.core.designsystem.ui.theme.SelfeeTheme
import com.abdulmateen.startertemplate.app.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    var shouldShowSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .apply {
                this.setKeepOnScreenCondition { shouldShowSplashScreen }
            }
        setContent {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
            LaunchedEffect(uiState.isCheckingAuth) {
                if(!uiState.isCheckingAuth) {
                    shouldShowSplashScreen = false
                }
            }
            SelfeeTheme {
                if (!uiState.isCheckingAuth){
                    AppNavGraph(
                        isSignedIn = uiState.isLoggedIn
                    )
                }

            }
        }

    }
}