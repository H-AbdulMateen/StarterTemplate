package com.abdulmateen.startertemplate.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.domain.models.TextIcon
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.listItems
import com.vanpra.composematerialdialogs.title

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

@Preview
@Composable
fun EmptyDataListPreview() {
    EmptyDataList()
}