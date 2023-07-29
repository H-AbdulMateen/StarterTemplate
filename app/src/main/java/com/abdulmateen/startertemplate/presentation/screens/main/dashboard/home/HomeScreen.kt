package com.abdulmateen.startertemplate.presentation.screens.main.dashboard.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.components.BasicTextFieldMultiline
import com.abdulmateen.startertemplate.presentation.components.ButtonRectangle
import com.abdulmateen.startertemplate.presentation.components.PhotoPickerDialog
import com.abdulmateen.startertemplate.utils.uriToBitmap
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun HomeScreen(
    navController: NavController,
    uiState: HomeScreenUIState,
    uiEvents: (HomeScreenEvents) -> Unit,
    apiEvents: MutableSharedFlow<HomeScreenApiEvents>
) {
    val context = LocalContext.current
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val dialogState = rememberMaterialDialogState()
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            it?.let { bitmap ->
                uiEvents(HomeScreenEvents.SetBitmap(bitmap))
            }
        }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            uriToBitmap(imageUri = uri, context = context)?.let { bitmap ->
                uiEvents(HomeScreenEvents.SetBitmap(bitmap))
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
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.add_your_request_details))
            Spacer(modifier = Modifier.height(8.dp))

            val painter = rememberAsyncImagePainter(model = uiState.bitmap)
            Box(contentAlignment = Alignment.Center) {

                Image(
                    painter = painter,
                    contentDescription = "Image",
                    modifier = Modifier
                        .height(100.dp)
                        .width(150.dp)
                        .background(White)
                        .clip(shape = RectangleShape)
                        .clickable(
                            onClick = {
                                dialogState.show()
                            }
                        ),
                    contentScale = ContentScale.FillBounds
                )
                if (painter.state is AsyncImagePainter.State.Error){
                    Icon(imageVector = Icons.Outlined.AddAPhoto, contentDescription = "AddAPhoto", tint = Black, modifier = Modifier.size(62.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            BasicTextFieldMultiline(
                text = uiState.requestDetail,
                hint = stringResource(id = R.string.add_request_details),
                onValueChange = { uiEvents(HomeScreenEvents.UpdateRequestDetails(it)) },
                onFocusChange = {},
                isHintVisible = uiState.requestDetail.isEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonRectangle(
                text = stringResource(id = R.string.create_request),
                onClick = { uiEvents(HomeScreenEvents.Submit) },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MaterialTheme.colorScheme.primary
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
}


@Preview(
    showBackground = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        uiState = HomeScreenUIState(),
        uiEvents = {},
        apiEvents = MutableSharedFlow<HomeScreenApiEvents>()
    )
}