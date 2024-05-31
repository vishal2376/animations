package com.vishal2376.animations.presentation.day3

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
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
fun ReflectionAnimation() {


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

    val bgColor by animateColorAsState(
        animationSpec = tween(500),
        targetValue = if (pager.currentPage % 2 == 0)
            Color(0xFFE2E7FF)
        else
            Color(0xFF090D1F),
        label = ""
    )
    val textColor by animateColorAsState(
        animationSpec = tween(500),
        targetValue = if (pager.currentPage % 2 == 0)
            Color.Black
        else
            Color.White,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pager,
            contentPadding = PaddingValues(75.dp),
            key = { places[it].resId },
        ) { index ->

            // animations
            val imgScale by animateFloatAsState(
                animationSpec = tween(500),
                targetValue = if (index == pager.currentPage) 1.3f else 1f,
                label = ""
            )
            val reflectionAlpha by animateFloatAsState(
                animationSpec = tween(500),
                targetValue = if (index == pager.currentPage) 0.8f else 0.2f,
                label = ""
            )
            val imgOffset by animateDpAsState(
                animationSpec = tween(500),
                targetValue = if (index == pager.currentPage) 80.dp else 4.dp,
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
                // Text
                Text(
                    text = places[index].name,
                    fontSize = 30.sp,
                    color = textColor,
                    letterSpacing = TextUnit(letterSpace, TextUnitType.Sp)
                )
                Spacer(modifier = Modifier.height(60.dp))

                // Original image
                Image(
                    modifier = Modifier
                        .height(250.dp)
                        .aspectRatio(3 / 4f)
                        .scale(imgScale)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(id = places[index].resId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                // Reflected image (flip Y-axis + gradient blend)
                Image(
                    painter = painterResource(id = places[index].resId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(250.dp)
                        .aspectRatio(3 / 4f)
                        .offset(y = imgOffset)
                        .scale(imgScale, -imgScale)
                        .clip(RoundedCornerShape(8.dp))
                        .graphicsLayer {
                            alpha = reflectionAlpha
                        }
                        .drawWithContent {
                            val overlayColors =
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color.White
                                )
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(overlayColors),
                                blendMode = BlendMode.DstIn
                            )
                        }
                )
            }
        }

    }
}

@Preview
@Composable
private fun ReflectionAnimationPreview() {
    ReflectionAnimation()
}