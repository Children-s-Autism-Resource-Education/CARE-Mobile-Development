package com.application.capstone.care.ui.navigation

import ForgotPasswordScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.application.capstone.care.ui.screens.DoctorScreen
import com.application.capstone.care.ui.screens.HomeScreen
import com.application.capstone.care.ui.screens.ResultScreen
import com.application.capstone.care.ui.screens.features.AutismStatusCheckScreen
import com.application.capstone.care.ui.screens.features.EmotionDetectionScreen
import com.application.capstone.care.ui.screens.login.LoginScreen
import com.application.capstone.care.ui.screens.setting.AboutUsScreen
import com.application.capstone.care.ui.screens.setting.EditProfileScreen
import com.application.capstone.care.ui.screens.setting.SettingScreen
import com.application.capstone.care.ui.screens.signup.SignUpScreen
import com.application.capstone.care.ui.screens.splashscreen.SplashScreen
import com.application.capstone.care.ui.screens.splashscreen.WelcomeScreen

sealed class NavigationRoutes(val route: String) {
    data object Splash : NavigationRoutes("splash_screen")
    data object Welcome : NavigationRoutes("welcome_screen")
    data object Login : NavigationRoutes("login_screen")
    data object SignUp : NavigationRoutes("sign_up_screen")
    data object ForgotPassword : NavigationRoutes("forgot_password_screen")
    data object Home : NavigationRoutes("home_screen")
    data object EmotionDetection : NavigationRoutes("emotion_detection_screen")
    data object AutismStatusCheck : NavigationRoutes("autism_status_check_screen")
    data object Result : NavigationRoutes("result_screen")
    data object Doctor : NavigationRoutes("doctor_screen")
    data object Settings : NavigationRoutes("settings_screen")
    data object AboutUs : NavigationRoutes("about_us_screen")
    data object EditProfile : NavigationRoutes("edit_profile_screen")
}

@Composable
fun NavRoutes(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Splash.route,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(NavigationRoutes.Welcome.route) {
                        popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(route = NavigationRoutes.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(route = NavigationRoutes.Login.route) {
            LoginScreen(
                navController = navController,
                onRegisterClick = {
                    // Navigate to the registration screen
                    navController.navigate(NavigationRoutes.SignUp.route) {
                        // Configuration to manage the navigation stack properly
                        popUpTo(NavigationRoutes.Login.route)
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(NavigationRoutes.ForgotPassword.route) {
                        popUpTo(NavigationRoutes.Login.route)
                    }
                })
        }
        composable(NavigationRoutes.SignUp.route) {
            SignUpScreen(
                navController = navController,
                onSignUpSuccess = {
                    // Navigate to the registration screen
                    navController.navigate(NavigationRoutes.Login.route)
                }
            )
        }
        composable(NavigationRoutes.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navController,
                onClicked = {
                    navController.navigate(NavigationRoutes.Login.route)
                }
            )
        }
        composable(NavigationRoutes.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationRoutes.EmotionDetection.route) {
            EmotionDetectionScreen(navController)
        }
        composable(NavigationRoutes.AutismStatusCheck.route) {
            AutismStatusCheckScreen(navController)
        }
        composable(NavigationRoutes.Result.route) {
            ResultScreen(navController)
        }
        composable(NavigationRoutes.Doctor.route) {
            DoctorScreen(navController)
        }
        composable(NavigationRoutes.Settings.route) {
            SettingScreen(navController)
        }
        composable(NavigationRoutes.AboutUs.route) {
            AboutUsScreen(navController)
        }
        composable(NavigationRoutes.EditProfile.route) {
            EditProfileScreen(
                navController = navController,
                onEditSuccess = {
                    navController.navigate(NavigationRoutes.Settings.route)
                }
            )
        }
    }
}
