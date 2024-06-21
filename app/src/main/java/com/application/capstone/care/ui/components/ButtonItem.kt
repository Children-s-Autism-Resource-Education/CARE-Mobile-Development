package com.application.capstone.care.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.capstone.care.ui.theme.Dimensions.buttonTextStyle
import com.application.capstone.care.ui.theme.MediumBlue
import com.application.capstone.care.ui.theme.White

@Composable
fun ButtonItem(label: String, onClick: () -> Unit = {}) {
    Text(
        text = label,
        color = White,
        style = buttonTextStyle,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = MediumBlue)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp, horizontal = 36.dp)
    )
}

@Preview
@Composable
fun PreviewButtonItem() {
    ButtonItem("Button")
}