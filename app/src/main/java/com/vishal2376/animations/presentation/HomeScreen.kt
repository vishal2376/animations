package com.vishal2376.animations.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vishal2376.animations.ui.theme.accentColor
import com.vishal2376.animations.ui.theme.darkBlue
import com.vishal2376.animations.ui.theme.lightBlue

val animationList = listOf(
	"Flip Card",
	"Card Slide",
	"Card Reflection",
	"Sweep Line Effect",
	"Carousel Slider"
)

@Composable
fun HomeScreen(navController: NavController) {
	Column(
		Modifier
			.fillMaxSize()
			.background(darkBlue)
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		Text(
			text = "30 Animations Challenge",
			color = Color.White,
			style = MaterialTheme.typography.titleLarge
		)

		animationList.forEachIndexed { index, title ->
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.clip(RoundedCornerShape(16.dp))
					.clickable {
						navController.navigate("day${index + 1}")
					}
					.background(lightBlue)
					.padding(16.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {

				Text(
					text = "Day ${index + 1}",
					color = accentColor,
					style = MaterialTheme.typography.titleMedium
				)

				Text(
					text = title,
					color = Color.White,
					style = MaterialTheme.typography.titleSmall
				)
			}
		}
	}
}