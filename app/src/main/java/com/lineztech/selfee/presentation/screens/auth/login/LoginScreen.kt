package com.lineztech.selfee.presentation.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lineztech.selfee.R
import com.lineztech.selfee.presentation.components.ButtonRectangle
import com.lineztech.selfee.presentation.components.TextFieldUnderlineEmail
import com.lineztech.selfee.presentation.components.TextFieldUnderlinePassword
import com.lineztech.selfee.presentation.navigation.HomeNavGraph
import com.lineztech.selfee.presentation.navigation.NavGraphs
import com.lineztech.selfee.presentation.navigation.ScreenRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.Locale

@Composable
fun LoginScreen(
    navController: NavController,
    uiState: LoginUIState,
    uiEvents: (LoginUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<LoginApiEvents>
) {
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_image_with_circles),
                contentDescription = "BackgroundImage",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.app_logo_large),
                    contentDescription = "SplashBgImage",
                    modifier = Modifier
                        .width(200.dp)
                        .height(75.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(18.dp))

                //Login Credential Card
                LoginCredentialsCard(
                    uiState = uiState,
                    uiEvents = uiEvents,
                    apiEvents = apiEvents,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                //Row don't have an account
                Row {
                    Text(
                        text = stringResource(id = R.string.dont_have_an_account),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate(ScreenRoute.SignUp.route)
                            })
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = true){
        apiEvents.collect{event ->
            when(event){
                LoginApiEvents.OnSuccess -> {
                    navController.popBackStack()
                    navController.navigate(NavGraphs.MAIN_GRAPH)
                }
                is LoginApiEvents.PopUpErrorMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun LoginCredentialsCard(
    uiState: LoginUIState,
    uiEvents: (LoginUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<LoginApiEvents>,
    navController: NavController
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.login).uppercase(Locale.ROOT),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = stringResource(id = R.string.login_to_your_account),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            //Email TextField
            TextFieldUnderlineEmail(
                text = uiState.email,
                onTextChange = { uiEvents(LoginUIEvents.UpdateEmail(it)) },
                placeholder = "someone@mail.com",
                label = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasEmailError,
                errorMessage = uiState.emailError
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Password Text Field
            TextFieldUnderlinePassword(
                text = uiState.password,
                onTextChange = { uiEvents(LoginUIEvents.UpdatePassword(it)) },
                placeholder = "*******",
                label = stringResource(id = R.string.password),
                passwordVisible = uiState.passwordVisibility,
                onClickPasswordVisibility = { uiEvents(LoginUIEvents.UpdatePasswordVisibilityStatus) },
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasPasswordError,
                errorMessage = uiState.passwordError
            )

            Spacer(modifier = Modifier.height(4.dp))

            //Forgot Password Text
            Text(
                text = stringResource(id = R.string.forgot_your_password),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Login Button
            ButtonRectangle(
                text = stringResource(id = R.string.login),
                onClick = {
                          uiEvents(LoginUIEvents.OnLoginClick)
//                          navController.navigate(NavGraphs.MAIN_GRAPH)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    val navController = rememberNavController()
    LoginScreen(
        navController = navController,
        uiState = LoginUIState(),
        uiEvents = {},
        apiEvents = MutableSharedFlow(),
    )
}