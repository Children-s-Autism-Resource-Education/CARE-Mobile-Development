package com.application.capstone.care.ui.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.R
import com.application.capstone.care.ui.components.ButtonItem
import com.application.capstone.care.ui.components.ConfirmPasswordTextField
import com.application.capstone.care.ui.components.EmailTextField
import com.application.capstone.care.ui.components.NameTextField
import com.application.capstone.care.ui.components.PasswordTextField
import com.application.capstone.care.ui.theme.Dimensions.HeadlineStyle
import com.application.capstone.care.ui.theme.Dimensions.bodyFontSize
import com.application.capstone.care.ui.theme.WhiteSmoke

@Composable
fun SignUpScreen(
    navController: NavHostController,
    onSignUpSuccess: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = HeadlineStyle
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Let's Get Started.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )
            Text(
                text = "Create an account",
                fontSize = bodyFontSize,
                color = WhiteSmoke,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )
            NameTextField(label = "Full Name")

            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                email = email.value,
                onEmailChange = { email.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                password = password.value,
                onPasswordChange = { password.value = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
            ConfirmPasswordTextField(
                confirmPassword = confirmPassword.value,
                onPasswordChange = { confirmPassword.value = it }
            )


            Spacer(modifier = Modifier.height(32.dp))
            SignUpButton(onClick = onSignUpSuccess)
        }
    }
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    ButtonItem(
        label = stringResource(R.string.sign_up),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController(), onSignUpSuccess = {})
}