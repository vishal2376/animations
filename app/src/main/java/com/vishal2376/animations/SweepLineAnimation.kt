package com.vishal2376.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SweepLineAnimation() {
    val bgColor = Color(0xFF060D1F)
    val textColor = Color(0xFF7CBBFF)
    Column(
        Modifier
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Your Favourite Cars", fontSize = 25.sp, color = textColor)
        SweepGradientImage(imgResId = R.drawable.megatron)
        SweepGradientImage(imgResId = R.drawable.bmw, maxImgRotation = 30f)
        SweepGradientImage(imgResId = R.drawable.bob_fastest_car)
    }
}


@Composable
fun SweepGradientImage(
    imgResId: Int,
    maxImgSize: Dp = 250.dp,
    maxImgRotation: Float = -10f
) {

    var animationPlayed by remember { mutableStateOf(false) }

    val lineOffset by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else -1.0f,
        animationSpec = tween(500),
        label = "",
    )

    val imgAlpha by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0.5f,
        animationSpec = tween(300),
        label = "",
    )

    val imgSize by animateDpAsState(
        targetValue = if (animationPlayed) maxImgSize else maxImgSize - 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    val imgRotation by animateFloatAsState(
        targetValue = if (animationPlayed) maxImgRotation - 20f else maxImgRotation,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ), label = ""
    )

    Box(
        modifier = Modifier
            .size(imgSize)
            .graphicsLayer { rotationZ = imgRotation }
            .clip(RoundedCornerShape(16.dp))
            .clickable { animationPlayed = !animationPlayed }
    ) {
        Image(
            painter = painterResource(id = imgResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .drawWithCache {
                    val lineWidth = 200

                    val startOffset = Offset(size.width * lineOffset, size.height * lineOffset)
                    val endOffset =
                        Offset(startOffset.x.plus(lineWidth), startOffset.y.plus(lineWidth))

                    val gradient = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(0.9f),
                            Color.Transparent
                        ),
                        start = startOffset,
                        end = endOffset
                    )

                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            gradient,
                            topLeft = Offset.Zero,
                            size = size
                        )
                    }
                }
                .graphicsLayer {
                    alpha = imgAlpha
                }
        )
    }
}

@Preview
@Composable
private fun SweepLineAnimationPreview() {
    SweepLineAnimation()
}