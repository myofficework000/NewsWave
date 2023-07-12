package com.example.newswithcleancode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newswithcleancode.R
import com.example.newswithcleancode.model.News
import com.example.newswithcleancode.ui.theme.Calisto
import com.example.newswithcleancode.ui.theme.RockWell
import com.example.newswithcleancode.utils.extractDate

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsBrief(data: News) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (img, content) = createRefs()

        val guide1 = createGuidelineFromStart(0.3f)

        GlideImage(
            model = data.image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .constrainAs(img) {
                    linkTo(top = content.top, bottom = content.bottom)
                    linkTo(start = parent.start, end = guide1)
                    height = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop
        ) {
            it.placeholder(R.drawable.baseline_more_horiz_24)
                .error(R.drawable.baseline_newspaper_24)
        }

        Column(
            Modifier.constrainAs(content) {
                linkTo(start = guide1, end = parent.end)
                width = Dimension.fillToConstraints
            },
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp, end = 4.dp),
                text = data.title,
                maxLines = 2,
                lineHeight = 18.sp,
                fontFamily = RockWell,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.padding(top = 6.dp, end = 4.dp),
                text = data.description ?: "",
                maxLines = 3,
                color = Black.copy(alpha = 0.7f),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Calisto
            )
            Spacer(Modifier.height(1.dp))
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = data.published.extractDate(),
                lineHeight = 18.sp,
                fontSize = 15.sp,
                color = Color.Gray,
                fontFamily = Calisto
            )
        }
    }
}