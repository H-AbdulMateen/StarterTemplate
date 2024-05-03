package com.abdulmateen.startertemplate.presentation.components.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.components.BasicTextFieldMultiline
import com.abdulmateen.startertemplate.presentation.components.BasicTextFieldWhiteBox
import com.abdulmateen.startertemplate.presentation.components.DateTextFieldUnderline
import com.abdulmateen.startertemplate.presentation.components.TextFieldOutlinedPassword
import com.abdulmateen.startertemplate.presentation.components.TextFieldOutlinedPhone
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderline
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderlineEmail
import com.abdulmateen.startertemplate.presentation.components.TextFieldUnderlinePassword

@Composable
fun TextFieldPreviews() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Underline Text Fields samples", style = MaterialTheme.typography.labelLarge)
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

        var dateTextFieldUnderline by remember{ mutableStateOf("") }
        DateTextFieldUnderline(
            text = dateTextFieldUnderline,
            onTextChange = {dateTextFieldUnderline = it},
            placeholder = "11/02/2024",
            label = "11/02/2024",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
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
        Text(text = "BasicTextFields samples", style = MaterialTheme.typography.labelLarge)
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
        Spacer(modifier = Modifier.height(16.dp))
        var basicTextFieldWhiteBox by remember { mutableStateOf("") }
        BasicTextFieldWhiteBox(
            text = basicTextFieldWhiteBox,
            hint = "BasicTextField White Box",
            onValueChange = {basicTextFieldWhiteBox = it},
            onFocusChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        ////////////////Outlined Text Fields/////////////
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "OutlinedTextField samples", style = MaterialTheme.typography.labelLarge)
        var textFieldOutlinedPhone by remember { mutableStateOf("") }
        TextFieldOutlinedPhone(
            text = textFieldOutlinedPhone,
            onTextChange = { textFieldOutlinedPhone = it },
            placeholder = "+00 000 000 0000",
            label = "+00 000 000 0000",
            modifier = Modifier.fillMaxWidth()
        )
        var textFieldOutlinedPassword by remember { mutableStateOf("") }
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
}


@Preview(showBackground = true)
@Composable
fun TextFieldPreviewsPreview() {
    TextFieldPreviews()
}