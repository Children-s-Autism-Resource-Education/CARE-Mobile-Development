package com.application.capstone.care.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.capstone.care.model.Articles
import com.application.capstone.care.ui.theme.CornflowerBlue
import com.application.capstone.care.ui.theme.Dimensions
import com.application.capstone.care.ui.theme.Dimensions.titleTextStyle
import com.application.capstone.care.ui.theme.MediumBlue
import com.application.capstone.care.ui.theme.TextColor
import com.application.capstone.care.ui.theme.WhiteSmoke

val articleCard = listOf(
    Articles(
        title = "Lorem Ipsum",
        content = "Lorem ipsum is placeholder text commonly used in the graphic."
    ),
    Articles(
        title = "Lorem Ipsum",
        content = "Lorem ipsum is placeholder text commonly used in the graphic."
    ),
    Articles(
        title = "Lorem Ipsum",
        content = "Lorem ipsum is placeholder text commonly used in the graphic."
    )
)

@Composable
fun ArticlesSection() {
    Column(
        modifier = Modifier.padding(
            start = Dimensions.horizontalPadding,
            end = Dimensions.horizontalPadding,
            top = Dimensions.verticalPadding
        )
    ) {
        Text(
            text = "Articles",
            color = MediumBlue,
            style = titleTextStyle,
        )
        Text(
            text = "Get insight and developmental support for your child!",
            color = CornflowerBlue,
            fontSize = Dimensions.contentFontSize
        )

        Spacer(modifier = Modifier.height(Dimensions.paddingStandard))

        LazyRow {
            items(articleCard.size) { index ->
                ArticleCardItem(index)
                Spacer(modifier = Modifier.width(Dimensions.cardSpacing))
            }
        }
    }
}

@Composable
fun ArticleCardItem(
    index: Int
) {
    val card = articleCard[index]
    Box(
        modifier = Modifier
            .padding(end = if (index == articleCard.size - 1) Dimensions.horizontalPadding else 0.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(Dimensions.cornerRadius))
                .background(color = WhiteSmoke)
                .width(260.dp)
                .height(140.dp)
                .padding(
                    vertical = Dimensions.verticalPadding,
                    horizontal = Dimensions.horizontalPadding
                )
        ) {
            Text(
                text = card.title,
                color = TextColor,
                fontWeight = FontWeight.Bold,
                fontSize = Dimensions.contentFontSize
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = card.content,
                color = TextColor,
                fontSize = Dimensions.bodyFontSize
            )
        }
    }
}

@Preview
@Composable
fun PreviewArticlesSection() {
    ArticlesSection()
}
