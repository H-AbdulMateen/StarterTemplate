package com.abdulmateen.startertemplate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.domain.models.TextIcon
import com.abdulmateen.startertemplate.utils.GenericDropdownModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.title
import kotlinx.coroutines.launch

@Composable
fun ButtonRectangle(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        modifier = modifier,
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun TextRectangleWithBg(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge.copy(
            color = contentColor
        ),
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 2.dp, horizontal = 16.dp)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithDrawer(
    onClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu, contentDescription = "MenuIcon",
                modifier = Modifier.clickable(onClick = onClick),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {}
    )
}

@Composable
fun PhotoPickerDialog(
    dialogState: MaterialDialogState,
    openCamera: () -> Unit,
    openGallery: () -> Unit
) {

    val items = listOf(
        TextIcon(stringResource(id = R.string.camera), Icons.Default.Camera),
        TextIcon(stringResource(id = R.string.gallery), Icons.Default.PhotoLibrary)
    )
    MaterialDialog(
        dialogState = dialogState,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        title(
            text = stringResource(id = R.string.pick_image_from),
            color = MaterialTheme.colorScheme.onBackground
        )
        listItems(list = items, onClick = { index, item ->
            if (index == 0) {
                openCamera()
            } else {
                openGallery()
            }
        }, closeOnClick = true,
            item = { index, textIcon ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = textIcon.icon,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = textIcon.text, color = MaterialTheme.colorScheme.onBackground)
                }
            }
        )
    }
}

@Composable
fun ErrorText(
    message: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = message,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall.copy(
            color = MaterialTheme.colorScheme.error
        )
    )
}

@Composable
fun DropdownWidget(
    modifier: Modifier = Modifier,
    list: ArrayList<String>,
    selectedItem: String,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedItem,
                style = MaterialTheme.typography.labelLarge.copy(color = Black)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "ArrowDropdown",
                tint = Black
            )
        }
    }


    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() })
    {
        list.forEachIndexed { index, s ->
            DropdownMenuItem(
                text = { Text(text = s) },
                onClick = {
                    onItemClick(s)
                    onDismiss()
                },
            )

        }
    }
}

@Composable
fun EmptyDataList() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_data_image),
            contentDescription = "NoDataImage",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = R.string.sorry_there_is_no_data_to_show),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogSample(
    openDialog: Boolean,
    closeDialog: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    colors: DatePickerColors = DatePickerDefaults.colors(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable ColumnScope.() -> Unit,
    selectedDate: (Long) -> Unit
) {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    // TODO demo how to read the selected date from the state.
    if (openDialog) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                closeDialog(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        closeDialog(false)
                        snackScope.launch {
                            datePickerState.selectedDateMillis?.let{
                                selectedDate(it)
                            }
//                            snackState.showSnackbar(
//                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
//                            )
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        closeDialog(false)
                    }
                ) {
                    Text(stringResource(id = R.string.cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun DialogConfirmExit(
    title: String = stringResource(id = R.string.alert),
    message: String = stringResource(id = R.string.are_you_sure_to_exit_from_app),
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(stringResource(id = R.string.yes))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel
            ) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        containerColor = Color.White
    )
}


@Composable
fun DropdownGeneric(
    modifier: Modifier = Modifier,
    list: List<GenericDropdownModel>,
    selectedItem: String,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onItemClick: (GenericDropdownModel) -> Unit,
    placeholder: String
) {
    var textFieldWidth by remember { mutableStateOf(IntSize.Zero) }
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier.onSizeChanged {
            textFieldWidth = it
        }
    ) {

        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedItem.ifEmpty { placeholder },
                    style = MaterialTheme.typography.labelLarge.copy(color = if (selectedItem.isNotEmpty()) Black else Color.LightGray),
                    maxLines = 1,
                    modifier = Modifier.weight(.4f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "ArrowDropdown",
                    tint = Black,
                    modifier = Modifier.weight(.1f)
                )
            }
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .then(with(LocalDensity.current) {
                    Modifier.width(textFieldWidth.width.toDp())
                })
                .background(color = Color.White),
        )
        {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(text = item.label, color = Black) },
                    onClick = {
                        onItemClick(item)
                        onDismiss()
                    },
                    modifier = Modifier.background(color = Color.White)
                )

            }
        }
    }
}

@Composable
fun SpacerH2(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(2.dp))
}
@Composable
fun SpacerH4(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(4.dp))
}
@Composable
fun SpacerH6(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(6.dp))
}
@Composable
fun SpacerH8(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(8.dp))
}
@Composable
fun SpacerH10(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(10.dp))
}
@Composable
fun SpacerH12(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(12.dp))
}

@Composable
fun SpacerH16(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.height(16.dp))
}
@Composable
fun SpacerW2(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(2.dp))
}
@Composable
fun SpacerW4(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(4.dp))
}
@Composable
fun SpacerW6(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(6.dp))
}
@Composable
fun SpacerW8(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(8.dp))
}
@Composable
fun SpacerW10(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(10.dp))
}
@Composable
fun SpacerW12(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(12.dp))
}

@Composable
fun SpacerW16(
    modifier: Modifier = Modifier
) {
    Spacer(modifier = modifier.width(16.dp))
}

@Preview
@Composable
fun EmptyDataListPreview() {
    EmptyDataList()
}
