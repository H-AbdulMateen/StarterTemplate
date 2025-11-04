package com.abdulmateen.startertemplate.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.core.designsystem.utils.PhoneVisualTransformation

@Composable
fun TextFieldUnderline(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String,
    hasError: Boolean = false,
    errorMessage: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true
    )
}

@Preview
@Composable
private fun TextFieldUnderlinePreview() {
    var textFieldUnderline by remember { mutableStateOf("") }
    TextFieldUnderline(
        text = textFieldUnderline,
        onTextChange = {textFieldUnderline = it},
        placeholder = "TextFieldUnderline",
        label = "TextFieldUnderline",
        modifier = Modifier.fillMaxWidth(),
        hasError = false,
        errorMessage = ""
    )
}

@Composable
fun DateTextFieldUnderline(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String,
    hasError: Boolean = false,
    errorMessage: String = "",
    onClick: () -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.clickable(
            onClick = onClick
        ),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        readOnly = true,
        enabled = false,
        trailingIcon = { Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "") },
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Preview
@Composable
private fun DateTextFieldUnderlinePreview() {
    var dateTextFieldUnderline by remember{ mutableStateOf("") }
    DateTextFieldUnderline(
        text = dateTextFieldUnderline,
        onTextChange = {dateTextFieldUnderline = it},
        placeholder = "11/02/2024",
        label = "11/02/2024",
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextFieldUnderlineEmail(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String,
    hasError: Boolean,
    errorMessage: String
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            autoCorrect = false
        ),
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        singleLine = true
    )
}

@Preview
@Composable
private fun TextFieldUnderlineEmailPreview() {
    var emailTextFieldUnderline by remember { mutableStateOf("") }
    TextFieldUnderlineEmail(
        text = emailTextFieldUnderline,
        onTextChange = {emailTextFieldUnderline = it},
        placeholder = stringResource(id = R.string.email_placeholder),
        label = stringResource(id = R.string.email_placeholder),
        hasError = false,
        errorMessage = "",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextFieldUnderlinePassword(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String,
    passwordVisible: Boolean,
    onClickPasswordVisibility: () -> Unit,
    hasError: Boolean,
    errorMessage: String
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = "PasswordTrailingIcon",
                modifier = Modifier.clickable(onClick = onClickPasswordVisibility)
            )
        },
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        singleLine = true
    )
}

@Preview
@Composable
private fun TextFieldUnderlinePasswordPreview() {
    var passwordTextFieldUnderline by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    TextFieldUnderlinePassword(
        text = passwordTextFieldUnderline,
        onTextChange = {passwordTextFieldUnderline = it},
        placeholder = stringResource(id = R.string.password_placeholder),
        label = stringResource(id = R.string.password_placeholder),
        hasError = false,
        errorMessage = "",
        modifier = Modifier.fillMaxWidth(),
        passwordVisible = passwordVisibility,
        onClickPasswordVisibility = { passwordVisibility = !passwordVisibility}
    )
}


@Composable
fun BasicTextFieldMultiline(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    backgroundColor: Color = White
) {
    Box(
        modifier = modifier.background(
            color = backgroundColor
        )
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onFocusChanged {
                    onFocusChange(it)
                },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            enabled = isEnabled,
            visualTransformation = visualTransformation
        )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = LightGray, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview
@Composable
private fun BasicTextFieldMultilinePreview() {
    var multilineTextField by remember { mutableStateOf("") }
    BasicTextFieldMultiline(
        text = multilineTextField,
        hint = "Multiline Text Field",
        onValueChange = {multilineTextField = it},
        onFocusChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = LightGray.copy(alpha = 0.2f)
    )
}
@Composable
fun BasicTextFieldWhiteBox(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = true,
    onFocusChange: (FocusState) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    backgroundColor: Color = White
) {
    Card(elevation = CardDefaults.cardElevation(
        defaultElevation = 2.dp
    ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Box {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .onFocusChanged {
                        onFocusChange(it)
                    },
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                enabled = isEnabled,
                visualTransformation = visualTransformation
            )
            if (isHintVisible) {
                Text(text = hint, style = textStyle, color = LightGray, textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Preview
@Composable
private fun BasicTextFieldWhiteBoxPreview() {
    var basicTextFieldWhiteBox by remember { mutableStateOf("") }
    BasicTextFieldWhiteBox(
        text = basicTextFieldWhiteBox,
        hint = "BasicTextField White Box",
        onValueChange = {basicTextFieldWhiteBox = it},
        onFocusChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextFieldOutlinedPhone(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String = "",
    hasError: Boolean = false,
    errorMessage: String = "",
    keyboardType: KeyboardType = KeyboardType.Phone,
    leadingIcon: ImageVector = Icons.Default.Phone,
    mask: String = "00 000 000 0000",
    maskNumber: Char = '0',
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        leadingIcon =  {
            Icon(
                imageVector = leadingIcon,
                contentDescription = "PasswordTrailingIcon",
                tint = LightGray.copy(alpha = 0.4f)
            )
        },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = LightGray,
            unfocusedTrailingIconColor = LightGray,
            focusedContainerColor = Color.Transparent,
            focusedTrailingIconColor = Color.Gray,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
        ),
        visualTransformation = PhoneVisualTransformation(mask = mask, maskNumber = maskNumber),
        singleLine = true
    )
}

@Preview
@Composable
private fun OutlinedTextFieldPhonePreview() {
    var textFieldOutlinedPhone by remember { mutableStateOf("") }
    TextFieldOutlinedPhone(
        text = textFieldOutlinedPhone,
        onTextChange = { textFieldOutlinedPhone = it },
        placeholder = "+00 000 000 0000",
        label = "+00 000 000 0000",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextFieldOutlinedPassword(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String = "",
    passwordVisible: Boolean,
    onClickPasswordVisibility: () -> Unit,
    hasError: Boolean,
    errorMessage: String
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = "PasswordTrailingIcon",
                modifier = Modifier.clickable(onClick = onClickPasswordVisibility),
            )
        },
        isError = hasError,
        supportingText = {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Lock, contentDescription = "LockIcon",
                tint = LightGray)
        },
        shape = MaterialTheme.shapes.small,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = LightGray,
            unfocusedTrailingIconColor = LightGray,
            focusedContainerColor = Color.Transparent,
            focusedTrailingIconColor = Color.Gray,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
        ),
        singleLine = true
    )
}

@Preview
@Composable
private fun OutlinedTextFieldPasswordPreview() {
    var textFieldOutlinedPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    TextFieldOutlinedPassword(
        text = textFieldOutlinedPassword,
        label = stringResource(id = R.string.password),
        onTextChange = {textFieldOutlinedPassword = it},
        placeholder = stringResource(id = R.string.password),
        passwordVisible = passwordVisibility,
        onClickPasswordVisibility = { passwordVisibility = !passwordVisibility },
        hasError = false,
        errorMessage = "Error Message",
        modifier = Modifier.fillMaxWidth()
    )
}
