package com.application.capstone.care.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.application.capstone.care.R

@Composable
fun GreetingSection(
    name: String = "Karin"
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_user_profile),
            contentDescription = "User Photo Profile",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(52.dp)
        )
        Spacer(
            modifier = Modifier
                .width(14.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hi, Welcome",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}