package com.application.capstone.care.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.ui.components.ArticlesSection
import com.application.capstone.care.ui.components.BottomNavigationBar
import com.application.capstone.care.ui.components.FeaturesSection
import com.application.capstone.care.ui.components.GreetingSection

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = "home_screen")
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavHostController) {
    Column {
        GreetingSection()
        ArticlesSection()
        FeaturesSection(navController = navController)
        ResultScreen(navController)
        DoctorScreen(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
