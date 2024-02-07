package com.abdulmateen.startertemplate.presentation.screens.auth.signup

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.components.ButtonRectangle
import com.abdulmateen.startertemplate.presentation.components.DatePickerDialogSample
import com.abdulmateen.startertemplate.presentation.components.DateTextFieldUnderline
import com.abdulmateen.startertemplate.presentation.components.PhotoPickerDialog
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderline
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderlineEmail
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderlinePassword
import com.abdulmateen.startertemplate.presentation.components.TextRectangleWithBg
import com.abdulmateen.startertemplate.presentation.screens.main.profile.ProfileUIEvents
import com.abdulmateen.startertemplate.utils.formatDateTimeStamp
import com.abdulmateen.startertemplate.utils.uriToBitmap
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.Instant

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

                val scope = rememberCoroutineScope()

                Spacer(modifier = Modifier.height(8.dp))

                        CustomerSignUpCard(
                            uiState = uiState,
                            uiEvents = uiEvents,
                            apiEvents = apiEvents
                        )

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerSignUpCard(
    uiState: SignUpUIState,
    uiEvents: (SignUpUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<SignUpApiEvents>
) {
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val dialogState = rememberMaterialDialogState()
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let { bitmap ->
                uiEvents(SignUpUIEvents.UpdateBitmap(bitmap))
            }
        }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uriToBitmap(imageUri = uri, context = context)?.let { bitmap ->
                uiEvents(SignUpUIEvents.UpdateBitmap(bitmap))
            }
        }
    )
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            hasCamPermission = granted
            cameraLauncher.launch()
        } else {
            dialogState.hide()
        }
    }
    //Date Picker Dialog state
    val datePickerDialog = rememberSaveable { mutableStateOf(false) }
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
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = uiState.bitmap,
                contentDescription = "Image",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(shape = CircleShape)
                    .background(Color.Gray)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable(
                        onClick = {
                            dialogState.show()
                        }
                    ),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(id = R.drawable.person),
                error = painterResource(id = R.drawable.person)
            )
            if (uiState.bitmapHasError) {
                Text(
                    text = uiState.bitmapError,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                )
            }


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
            //DatePicker Field
            DateTextFieldUnderline(
                text = uiState.dateOfBirth,
                onTextChange = {},
                placeholder = stringResource(id = R.string.date_of_birth),
                label = stringResource(
                    id = R.string.date_of_birth
                ),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    datePickerDialog.value = true
                }
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

    PhotoPickerDialog(
        dialogState = dialogState,
        openCamera = {
            launcher.launch(Manifest.permission.CAMERA)
        },
        openGallery = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }
    )
        DatePickerDialogSample(
            openDialog = datePickerDialog.value,
            closeDialog = {
                          datePickerDialog.value = it
            },
            onDismissRequest = {},
            confirmButton = {},
            dismissButton = {},
            content = {},
            selectedDate = {
                val selectedDate = formatDateTimeStamp(timestamp = it, pattern = "dd/MM/yyyy")
                selectedDate?.let {date ->
                    uiEvents(SignUpUIEvents.UpdateDateOfBirth(selectedDate = date)) }

                }
        )
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