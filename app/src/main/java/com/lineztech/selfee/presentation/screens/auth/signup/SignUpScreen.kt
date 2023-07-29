package com.lineztech.selfee.presentation.screens.auth.signup

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lineztech.selfee.R
import com.lineztech.selfee.presentation.components.ButtonRectangle
import com.lineztech.selfee.presentation.components.TextFieldUnderline
import com.lineztech.selfee.presentation.components.TextFieldUnderlineEmail
import com.lineztech.selfee.presentation.components.TextFieldUnderlinePassword
import com.lineztech.selfee.presentation.components.TextRectangleWithBg
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    uiState: SignUpUIState,
    uiEvents: (SignUpUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<SignUpApiEvents>
) {
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
                val userTypes = arrayListOf("Patient", "Doctor")
                val selectedUserType = remember { mutableStateOf(userTypes[0]) }
                val pagerState = rememberPagerState(pageCount = {2})
                val scope = rememberCoroutineScope()

                LazyRow(
                    modifier = Modifier
                        .background(color = White, shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 2.dp, horizontal = 8.dp)
                ) {
                    itemsIndexed(userTypes) { index, item ->
                        CustomToggleButton(
                            item = item,
                            isSelected = selectedUserType.value == item,
                            onSelectionChange = {
                                selectedUserType.value = item
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                val fling = PagerDefaults.flingBehavior(
                    state = pagerState,
                    pagerSnapDistance = PagerSnapDistance.atMost(10)
                )
                //Pager
                HorizontalPager(
                    state = pagerState,
                    flingBehavior = fling
                ) {
                    if (pagerState.currentPage == 0) {
                        CustomerSignUpCard(
                            uiState = uiState,
                            uiEvents = uiEvents,
                            apiEvents = apiEvents
                        )
                    } else {
                        SignUpCredentialsCard1(
                            uiState = uiState,
                            uiEvents = uiEvents,
                            apiEvents = apiEvents
                        )
                    }
                }

                LaunchedEffect(key1 = pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { pageNumber ->
                        selectedUserType.value = userTypes[pageNumber]
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                //Row don't have an account
                Row {
                    Text(
                        text = stringResource(id = R.string.already_have_an_account),
                        style = MaterialTheme.typography.labelLarge
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigateUp()
                            }
                        )
                    )
                }
            }

        }
    }


}

@Composable
fun CustomToggleButton(item: String, isSelected: Boolean, onSelectionChange: () -> Unit) {
    TextRectangleWithBg(
        text = item, onClick = onSelectionChange,
        backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else White,
        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
    )
}


@Composable
fun CustomerSignUpCard(
    uiState: SignUpUIState,
    uiEvents: (SignUpUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<SignUpApiEvents>
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
                .padding(12.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Text(
                text = stringResource(id = R.string.create_your_account),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(4.dp))
            TextFieldUnderline(
                text = uiState.fullName,
                onTextChange = {
                    uiEvents(SignUpUIEvents.UpdateFullName(it))
                },
                placeholder = stringResource(id = R.string.full_name),
                label = stringResource(
                    id = R.string.full_name
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            //Email TextField
            TextFieldUnderlineEmail(
                text = uiState.email,
                onTextChange = { uiEvents(SignUpUIEvents.UpdateEmail(it)) },
                placeholder = "someone@mail.com",
                label = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasEmailError,
                errorMessage = uiState.emailError
            )

            Spacer(modifier = Modifier.height(4.dp))
            TextFieldUnderline(
                text = uiState.phoneNumber,
                onTextChange = {
                    uiEvents(SignUpUIEvents.UpdatePhoneNumber(it))
                },
                placeholder = stringResource(id = R.string.phone_number),
                label = stringResource(
                    id = R.string.phone_number
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(4.dp))

            TextFieldUnderline(
                text = uiState.address,
                onTextChange = {
                    uiEvents(SignUpUIEvents.UpdateAddress(it))
                },
                placeholder = stringResource(id = R.string.address),
                label = stringResource(
                    id = R.string.address
                ),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            //Password Text Field
            Row(modifier = Modifier.fillMaxWidth()) {
                TextFieldUnderlinePassword(
                    text = uiState.password,
                    onTextChange = { uiEvents(SignUpUIEvents.UpdatePassword(it)) },
                    placeholder = "*******",
                    label = stringResource(id = R.string.password),
                    passwordVisible = uiState.passwordVisibility,
                    onClickPasswordVisibility = { uiEvents(SignUpUIEvents.UpdatePasswordVisibilityStatus) },
                    modifier = Modifier
                        .padding(end = 3.dp)
                        .weight(.1f),
                    hasError = uiState.hasPasswordError,
                    errorMessage = uiState.passwordError
                )
                TextFieldUnderlinePassword(
                    text = uiState.confirmPassword,
                    onTextChange = { uiEvents(SignUpUIEvents.UpdateConfirmPassword(it)) },
                    placeholder = "*******",
                    label = stringResource(id = R.string.password),
                    passwordVisible = uiState.confirmPasswordVisibility,
                    onClickPasswordVisibility = { uiEvents(SignUpUIEvents.UpdateConfirmPasswordVisibilityStatus) },
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .weight(.1f),
                    hasError = uiState.hasConfirmPasswordError,
                    errorMessage = uiState.confirmPasswordError
                )
            }


            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = uiState.hasTermsAccepted,
                    onCheckedChange = { uiEvents(SignUpUIEvents.UpdateTermsAcceptedStatus) }
                )
                Text(
                    text = stringResource(id = R.string.terms_and_conditions),
                    fontSize = 11.sp
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            //Register Button
            ButtonRectangle(
                text = stringResource(id = R.string.register),
                onClick = {
                    uiEvents(SignUpUIEvents.OnSignUpClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SignUpCredentialsCard1(
    uiState: SignUpUIState,
    uiEvents: (SignUpUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<SignUpApiEvents>
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = stringResource(id = R.string.create_your_account),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))
                TextFieldUnderline(
                    text = uiState.fullName,
                    onTextChange = {
                        uiEvents(SignUpUIEvents.UpdateFullName(it))
                    },
                    placeholder = stringResource(id = R.string.full_name),
                    label = stringResource(
                        id = R.string.full_name
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                //Email TextField
                TextFieldUnderlineEmail(
                    text = uiState.email,
                    onTextChange = { uiEvents(SignUpUIEvents.UpdateEmail(it)) },
                    placeholder = "someone@mail.com",
                    label = stringResource(id = R.string.email),
                    modifier = Modifier.fillMaxWidth(),
                    hasError = uiState.hasEmailError,
                    errorMessage = uiState.emailError
                )

                Spacer(modifier = Modifier.height(4.dp))
                TextFieldUnderline(
                    text = uiState.phoneNumber,
                    onTextChange = {
                        uiEvents(SignUpUIEvents.UpdatePhoneNumber(it))
                    },
                    placeholder = stringResource(id = R.string.phone_number),
                    label = stringResource(
                        id = R.string.phone_number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Phone
                )
                Spacer(modifier = Modifier.height(4.dp))

                TextFieldUnderline(
                    text = uiState.address,
                    onTextChange = {
                        uiEvents(SignUpUIEvents.UpdateAddress(it))
                    },
                    placeholder = stringResource(id = R.string.address),
                    label = stringResource(
                        id = R.string.address
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(4.dp))
                //Password Text Field
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextFieldUnderlinePassword(
                        text = uiState.password,
                        onTextChange = { uiEvents(SignUpUIEvents.UpdatePassword(it)) },
                        placeholder = "*******",
                        label = stringResource(id = R.string.password),
                        passwordVisible = uiState.passwordVisibility,
                        onClickPasswordVisibility = { uiEvents(SignUpUIEvents.UpdatePasswordVisibilityStatus) },
                        modifier = Modifier
                            .padding(end = 3.dp)
                            .weight(.1f),
                        hasError = uiState.hasPasswordError,
                        errorMessage = uiState.passwordError
                    )
                    TextFieldUnderlinePassword(
                        text = uiState.confirmPassword,
                        onTextChange = { uiEvents(SignUpUIEvents.UpdateConfirmPassword(it)) },
                        placeholder = "*******",
                        label = stringResource(id = R.string.password),
                        passwordVisible = uiState.confirmPasswordVisibility,
                        onClickPasswordVisibility = { uiEvents(SignUpUIEvents.UpdateConfirmPasswordVisibilityStatus) },
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .weight(.1f),
                        hasError = uiState.hasConfirmPasswordError,
                        errorMessage = uiState.confirmPasswordError
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = uiState.hasTermsAccepted,
                        onCheckedChange = { uiEvents(SignUpUIEvents.UpdateTermsAcceptedStatus) }
                    )
                    Text(
                        text = stringResource(id = R.string.terms_and_conditions),
                        fontSize = 11.sp
                    )
                }


                Spacer(modifier = Modifier.height(12.dp))


                //Register Button
                ButtonRectangle(
                    text = stringResource(id = R.string.register),
                    onClick = {
                        uiEvents(SignUpUIEvents.OnSignUpClick)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPrev() {
    val navController = rememberNavController()
    SignUpScreen(
        navController = navController,
        uiState = SignUpUIState(),
        uiEvents = {},
        apiEvents = MutableSharedFlow()
    )
}