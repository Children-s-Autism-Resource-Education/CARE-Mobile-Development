package com.application.capstone.care.ui.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.R
import com.application.capstone.care.ui.components.ButtonItem
import com.application.capstone.care.ui.components.ConfirmPasswordTextField
import com.application.capstone.care.ui.components.EmailTextField
import com.application.capstone.care.ui.components.NameTextField
import com.application.capstone.care.ui.components.PasswordTextField
import com.application.capstone.care.ui.theme.Black
import com.application.capstone.care.ui.theme.Dimensions.titleTextStyle

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    onEditSuccess: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Back Arrow",
                tint = Black,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        navController.navigateUp()
                    }
            )
            Text(
                text = "Edit Profile",
                style = titleTextStyle,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
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
            EditProfileButton(onClick = onEditSuccess)
        }
    }
}

@Composable
fun EditProfileButton(onClick: () -> Unit) {
    ButtonItem(
        label = stringResource(R.string.edit_profile_button),
        onClick = onClick
    )
}

@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController(), onEditSuccess = {})
}