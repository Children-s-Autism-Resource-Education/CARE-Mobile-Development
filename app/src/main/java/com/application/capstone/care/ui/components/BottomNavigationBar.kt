package com.application.capstone.care.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import com.application.capstone.care.R
import com.application.capstone.care.model.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String
) {
    val items = listOf(
        NavigationItem(
            "Home",
            R.drawable.ic_home,
            "home_screen"
        ),
        NavigationItem(
            "Result",
            R.drawable.ic_result,
            "result_screen"
        ),
        NavigationItem(
            "Doctor",
            R.drawable.ic_doctor,
            "doctor_screen"
        ),
        NavigationItem(
            "Setting",
            R.drawable.ic_user_setting,
            "settings_screen"
        )
    )

    NavigationBar {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if (currentRoute == item.route) colorResource(id = R.color.mediumsBlue) else colorResource(id = R.color.lightGray)
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                        // Ensure only one instance of a destination is in the back stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}
