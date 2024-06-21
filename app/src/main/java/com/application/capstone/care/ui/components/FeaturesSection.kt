package com.application.capstone.care.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.model.Features
import com.application.capstone.care.ui.navigation.NavigationRoutes
import com.application.capstone.care.ui.theme.CornflowerBlue
import com.application.capstone.care.ui.theme.Dimensions
import com.application.capstone.care.ui.theme.Dimensions.titleTextStyle
import com.application.capstone.care.ui.theme.MediumBlue
import com.application.capstone.care.ui.theme.White

val features = listOf(
    Features(
        "Check and Detect Your Child's Emotions!",
        NavigationRoutes.EmotionDetection.route
    ),
    Features(
        "Check Your Child's Autism Status!",
        NavigationRoutes.AutismStatusCheck.route
    ),
)

@Composable
fun FeaturesSection(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(
            start = Dimensions.horizontalPadding,
            end = Dimensions.horizontalPadding,
            top = Dimensions.verticalPadding
        )
    ) {
        Text(
            text = "Features",
            color = MediumBlue,
            style = titleTextStyle,
        )
        Text(
            text = "Check Your Child's Status and Emotional State!",
            color = CornflowerBlue,
            fontSize = Dimensions.contentFontSize
        )

        Spacer(modifier = Modifier.height(Dimensions.verticalPadding))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = Dimensions.paddingStandard)
        ) {
            items(features.size) { index ->
                FeatureCardItem(features[index], navController)
                Spacer(modifier = Modifier.height(Dimensions.cardSpacing))
            }
        }
    }
}

@Composable
fun FeatureCardItem(feature: Features, navController: NavHostController) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimensions.cornerRadius))
            .background(color = MediumBlue)
            .fillMaxWidth()
            .height(180.dp)
            .padding(
                vertical = Dimensions.verticalPadding,
                horizontal = Dimensions.horizontalPadding
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = feature.description,
                color = White,
                style = titleTextStyle
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "Here",
                    color = MediumBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimensions.contentFontSize,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = White)
                        .padding(vertical = 18.dp, horizontal = 36.dp)
                        .clickable {
                            try {
                                navController.navigate(feature.route)
                                Log.d("FeatureButton", "Navigating to: ${feature.route}")
                            } catch (e: Exception) {
                                Log.e("FeatureButton", "Navigation error: ${e.localizedMessage}")
                            }
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFeaturesSection() {
    FeaturesSection(navController = rememberNavController())
}
