package com.lineztech.selfee.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldUnderline(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String,
    hasError: Boolean = false,
    errorMessage: String = "",
    keyboardType: KeyboardType = KeyboardType.Text
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
        )
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
        }
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
        }
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
@Composable
fun BasicTextFieldWhiteBox(
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
            Text(text = hint, style = textStyle, color = LightGray)
        }
    }
}