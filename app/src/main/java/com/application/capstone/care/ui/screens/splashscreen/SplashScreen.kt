package com.application.capstone.care.ui.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.capstone.care.R
import com.application.capstone.care.ui.theme.MediumBlue
import com.application.capstone.care.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Delay for 3 seconds
        onSplashFinished()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MediumBlue),
        contentAlignment = Alignment.Center
    ) {
        // Layer 1: Background Image
        Image(
            painter = painterResource(id = R.drawable.img_family),
            contentDescription = null,
            modifier = Modifier
                .size(375.dp)
                .graphicsLayer { alpha = 0.3f },
            contentScale = ContentScale.Fit
        )

        // Layer 2: Foreground Image and Text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_white_logo),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(125.dp)
            )
            Text(
                text = "CARE",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onSplashFinished = {})
}