package com.application.capstone.care.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.capstone.care.ui.theme.CARETheme
import com.application.capstone.care.ui.theme.Dimensions
import com.application.capstone.care.ui.theme.WhiteSmoke

@Composable
fun CustomTextField(
    label: String,
    keyboardType: KeyboardType,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        Text(text = label)
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    WhiteSmoke,
                    RoundedCornerShape(Dimensions.cornerRadius)
                )
                .padding(vertical = 16.dp, horizontal = 12.dp)
        )
    }
}

@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
    CustomTextField(
        label = "Email Address",
        keyboardType = KeyboardType.Email,
        value = email,
        onValueChange = onEmailChange
    )
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    CustomTextField(
        label = "Password",
        keyboardType = KeyboardType.Password,
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ConfirmPasswordTextField(confirmPassword: String, onPasswordChange: (String) -> Unit) {
    CustomTextField(
        label = "Confirm Password",
        keyboardType = KeyboardType.Password,
        value = confirmPassword,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun NameTextField(label: String) {
    val (username, setUsername) = remember { mutableStateOf("") }
    CustomTextField(
        label = label,
        keyboardType = KeyboardType.Text,
        value = username,
        onValueChange = setUsername
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    CARETheme {
        Column(modifier = Modifier.padding(16.dp)) {
            val (text, setText) = remember { mutableStateOf("Preview Text") }
            CustomTextField(
                label = "Label",
                keyboardType = KeyboardType.Text,
                value = text,
                onValueChange = setText
            )
        }
    }
}
