package com.application.capstone.care.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.R
import com.application.capstone.care.ui.components.ButtonItem
import com.application.capstone.care.ui.navigation.NavigationRoutes
import com.application.capstone.care.ui.theme.Dimensions.HeadlineStyle
import com.application.capstone.care.ui.theme.Dimensions.contentFontSize
import com.application.capstone.care.ui.theme.Dimensions.imageSize
import com.application.capstone.care.ui.theme.Dimensions.titleTextStyle
import com.application.capstone.care.ui.theme.MediumBlue

@Composable
fun LoginScreen(
    navController: NavHostController,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))

    val email by loginViewModel.email
    val password by loginViewModel.password
    val loginState by loginViewModel.loginState.collectAsState()

    //Observe login state and handle navigation
    LaunchedEffect(loginState) {
        if (loginState is LoginState.Success) {
            navController.navigate(NavigationRoutes.Home.route) {
                popUpTo(NavigationRoutes.Login.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (loginState is LoginState.Loading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginImage()
                Spacer(modifier = Modifier.height(16.dp))

                LoginTitle()
                Spacer(modifier = Modifier.height(32.dp))

                LoginSubtitle()
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = email,
                    onValueChange = { loginViewModel.onEmailChange(it) },
                    label = { Text("Email Address") },
                    singleLine = true,
                    isError = loginViewModel.emailError.value != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Red
                    )
                )
                if (loginViewModel.emailError.value != null) {
                    Text(
                        text = loginViewModel.emailError.value ?: "",
                        color = Color.Red,
                        style = MaterialTheme.typography.caption
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    singleLine = true,
                    isError = loginViewModel.passwordError.value != null,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Red
                    )
                )
                if (loginViewModel.passwordError.value != null) {
                    Text(
                        text = loginViewModel.passwordError.value ?: "",
                        color = Color.Red,
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                SignUpAndForgotPassword(onRegisterClick, onForgotPasswordClick)
                Spacer(modifier = Modifier.height(24.dp))

                ButtonItem(
                    label = "Login",
                    onClick = { loginViewModel.login() }
                )

                when (loginState) {
                    is LoginState.Success -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "" +
                                    "Login successful!",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    is LoginState.Error -> {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Error: ${(loginState as LoginState.Error).message}",
                            color = Color.Red,
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun LoginImage() {
    Image(
        painter = painterResource(id = R.drawable.img_login),
        contentDescription = stringResource(R.string.login_image),
        contentScale = ContentScale.Fit,
        modifier = imageSize
    )
}

@Composable
fun LoginTitle() {
    Text(
        text = stringResource(id = R.string.login_title),
        style = HeadlineStyle
    )
}

@Composable
fun LoginSubtitle() {
    Text(
        text = stringResource(id = R.string.login_subtitle),
        style = titleTextStyle,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun SignUpAndForgotPassword(onRegisterClick: () -> Unit, onForgotPasswordClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                fontSize = contentFontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.sign_up)),
                onClick = { onRegisterClick() },
                style = LocalTextStyle.current.copy(
                    color = Color(0xFF615EFC),
                    fontSize = contentFontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            ClickableText(
                text = AnnotatedString(stringResource(id = R.string.forgot_password)),
                onClick = { onForgotPasswordClick() },
                style = LocalTextStyle.current.copy(
                    color = MediumBlue,
                    fontSize = contentFontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onRegisterClick = {}, onForgotPasswordClick = {}, navController = rememberNavController()
    )
}
