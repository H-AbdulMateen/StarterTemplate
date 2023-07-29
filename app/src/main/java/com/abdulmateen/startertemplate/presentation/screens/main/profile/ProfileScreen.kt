package com.abdulmateen.startertemplate.presentation.screens.main.profile

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.components.BasicTextFieldWhiteBox
import com.abdulmateen.startertemplate.presentation.components.ButtonRectangle
import com.abdulmateen.startertemplate.presentation.components.DropdownWidget
import com.abdulmateen.startertemplate.presentation.components.ErrorText
import com.abdulmateen.startertemplate.presentation.components.PhotoPickerDialog
import com.abdulmateen.startertemplate.presentation.ui.theme.blueLinkColor
import com.abdulmateen.startertemplate.utils.uriToBitmap
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun ProfileScreen(
    navController: NavController,
    uiEvents: (ProfileUIEvents) -> Unit,
    uiState: ProfileUIState,
    apiEvents: MutableSharedFlow<ProfileApiEvents>
) {
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val dialogState = rememberMaterialDialogState()
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let { bitmap ->
                uiEvents(ProfileUIEvents.UpdateBitmap(bitmap))
            }
        }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uriToBitmap(imageUri = uri, context = context)?.let { bitmap ->
                uiEvents(ProfileUIEvents.UpdateBitmap(bitmap))
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
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.change_password),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = blueLinkColor,
                    textAlign = TextAlign.End
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        alignment = Alignment.End
                    )
                    .clickable(
                        onClick = {}
                    ),
            )
            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = uiState.bitmap ?: uiState.imageUrl,
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.name_and_surname),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(2.dp))
            BasicTextFieldWhiteBox(
                text = uiState.fullName,
                hint = "",
                onValueChange = { uiEvents(ProfileUIEvents.UpdateFullName(it)) },
                onFocusChange = {},
                modifier = Modifier.clip(shape = RoundedCornerShape(4.dp))
            )
            if (uiState.hasFullNameError) {
                ErrorText(message = uiState.fullNameError)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.e_mail),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(2.dp))
            BasicTextFieldWhiteBox(
                text = uiState.email,
                hint = "",
                onValueChange = { uiEvents(ProfileUIEvents.UpdateEmail(it)) },
                onFocusChange = {},
                modifier = Modifier.clip(shape = RoundedCornerShape(4.dp)),
                keyboardType = KeyboardType.Email
            )

            if (uiState.hasEmailError) {
                ErrorText(message = uiState.emailError)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.mobile),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(2.dp))
            BasicTextFieldWhiteBox(
                text = uiState.phoneNumber,
                hint = "",
                onValueChange = { uiEvents(ProfileUIEvents.UpdatePhoneNumber(it)) },
                onFocusChange = {},
                modifier = Modifier.clip(shape = RoundedCornerShape(4.dp)),
                keyboardType = KeyboardType.Phone
            )

            if (uiState.hasPhoneNumberError) {
                ErrorText(message = uiState.phoneNumberError)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.address),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(2.dp))
            BasicTextFieldWhiteBox(
                text = uiState.address,
                hint = "",
                onValueChange = { uiEvents(ProfileUIEvents.UpdateAddress(it)) },
                onFocusChange = {},
                modifier = Modifier.clip(shape = RoundedCornerShape(4.dp))
            )

            if (uiState.hasAddressError) {
                ErrorText(message = uiState.addressError)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.postal_code),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(.1f)
                )
                Text(
                    text = stringResource(id = R.string.city),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .weight(.1f)
                        .padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth()) {

                var isPostalCodeMenuExpanded by rememberSaveable { mutableStateOf(false) }
                val list = arrayListOf("1000", "1001", "1002", "1003")
                DropdownWidget(
                    list = list,
                    selectedItem = uiState.postalCode,
                    expanded = isPostalCodeMenuExpanded,
                    onDismiss = { isPostalCodeMenuExpanded = false },
                    onItemClick = { uiEvents(ProfileUIEvents.UpdatePostalCode(it)) },
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(color = White)
                        .weight(.1f)
                        .clickable(onClick = {
                            isPostalCodeMenuExpanded = true
                        })
                )
                var isCityMenuExpanded by rememberSaveable { mutableStateOf(false) }
                val citites = arrayListOf("Lahore", "Karachi", "Islamabad", "Peshawer")
                DropdownWidget(
                    list = citites,
                    selectedItem = uiState.city,
                    expanded = isCityMenuExpanded,
                    onDismiss = { isCityMenuExpanded = false },
                    onItemClick = { uiEvents(ProfileUIEvents.UpdateCity(it)) },
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(color = White)
                        .weight(.1f)
                        .clickable(onClick = {
                            isCityMenuExpanded = true
                        })
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            ButtonRectangle(text = stringResource(id = R.string.save), onClick = {  },
            modifier = Modifier.fillMaxWidth())
            
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

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController(),
        uiEvents = {},
        uiState = ProfileUIState(),
        apiEvents = MutableSharedFlow()
    )
}