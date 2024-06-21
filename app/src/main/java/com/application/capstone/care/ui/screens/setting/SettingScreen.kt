package com.application.capstone.care.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.R
import com.application.capstone.care.ui.components.ButtonItem
import com.application.capstone.care.ui.components.EmailTextField
import com.application.capstone.care.ui.components.PasswordTextField
import com.application.capstone.care.ui.navigation.NavigationRoutes
import com.application.capstone.care.ui.theme.Black
import com.application.capstone.care.ui.theme.Dimensions
import com.application.capstone.care.ui.theme.Dimensions.paddingStandard

@Composable
fun SettingScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingStandard)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingStandard)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Black,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        navController.navigateUp()
                    }
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_user_profile),
            contentDescription = "User Photo Profile",
            tint = Black,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Edit Profile",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
                .clickable {
                    navController.navigate(NavigationRoutes.EditProfile.route)
                }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.padding(
                start = Dimensions.horizontalPadding,
                end = Dimensions.horizontalPadding
            )
        ) {
            // Email TextField
            EmailTextField(
                email = email.value,
                onEmailChange = { email.value = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Password TextField
            PasswordTextField(
                password = password.value,
                onPasswordChange = { password.value = it }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // About Us
            Text(
                text = "About Us",
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier
                    .clickable {
                        navController.navigate(NavigationRoutes.AboutUs.route)
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { /* Handle logout */ }
                    .padding(16.dp)
            ) {
                ButtonItem(label = "Logout")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingScreen(navController = rememberNavController())
}