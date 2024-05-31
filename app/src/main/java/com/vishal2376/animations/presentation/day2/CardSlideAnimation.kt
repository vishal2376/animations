package com.vishal2376.animations.presentation.day2

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vishal2376.animations.R
import com.vishal2376.animations.presentation.common.Place


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardSlideAnimation() {

    val places = listOf(
        Place("India", R.drawable.img1),
        Place("Italy", R.drawable.img2),
        Place("Turkey", R.drawable.img3),
        Place("Spain", R.drawable.img4),
        Place("Germany", R.drawable.img5),
    )

    val pager = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { places.size }
    )
    var currentImage by remember { mutableStateOf(places[pager.initialPage]) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Crossfade(
            targetState = currentImage.resId,
            label = "",
            animationSpec = tween(500)
        ) { targetState ->
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 0.5f
                    },
                painter = painterResource(id = targetState),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pager,
            contentPadding = PaddingValues(64.dp),
            key = { places[it].resId },
        ) { index ->
            currentImage = places[pager.currentPage]

            val imgScale by animateFloatAsState(
                animationSpec = tween(500),
                targetValue = if (index == pager.currentPage) 1.3f else 1f,
                label = ""
            )
            val letterSpace by animateFloatAsState(
                animationSpec = tween(600),
                targetValue = if (index == pager.currentPage) 8f else 0f,
                label = ""
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(3 / 4f)
                        .scale(imgScale)
                        .shadow(32.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = places[index].resId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = places[index].name,
                    fontSize = 25.sp,
                    color = Color.White,
                    letterSpacing = TextUnit(letterSpace, TextUnitType.Sp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardSlideAnimationPreview() {
    CardSlideAnimation()
}