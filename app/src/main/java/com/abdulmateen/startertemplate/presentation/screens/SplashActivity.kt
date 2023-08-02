package com.abdulmateen.startertemplate.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.ui.theme.SelfeeTheme
import com.abdulmateen.startertemplate.presentation.ui.theme.splashBackgroundColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen()
        setContent {

            SelfeeTheme {
                LaunchedEffect(key1 = true){
                    delay(3000)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
                SplashScreenBox()
            }
        }
    }
}

@Composable
fun SplashScreenBox() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = splashBackgroundColor),
    contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "SplashBgImage",
            modifier = Modifier
                .width(200.dp)
                .height(75.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
fun SplashScreenView() {
    SplashScreenBox()
}
