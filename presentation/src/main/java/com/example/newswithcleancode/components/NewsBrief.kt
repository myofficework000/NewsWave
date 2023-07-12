package com.example.newswithcleancode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newswithcleancode.R
import com.example.newswithcleancode.model.News

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
            Modifier.constrainAs(content){
                linkTo(start = guide1, end = parent.end)
                width = Dimension.fillToConstraints
            },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(data.title, fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(data.description, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}