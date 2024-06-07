package com.vishal2376.animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vishal2376.animations.presentation.HomeScreen
import com.vishal2376.animations.presentation.day1.FlipAnimation
import com.vishal2376.animations.presentation.day2.CardSlideAnimation
import com.vishal2376.animations.presentation.day3.ReflectionAnimation
import com.vishal2376.animations.presentation.day4.SweepLineAnimation
import com.vishal2376.animations.presentation.day5.CircularRevealAnimation
import com.vishal2376.animations.ui.theme.AnimationsTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val navController = rememberNavController()

			AnimationsTheme {
				NavHost(navController = navController, startDestination = "home") {
					composable("home") {
						HomeScreen(navController = navController)
					}

					composable("day1") {
						FlipAnimation()
					}

					composable("day2") {
						CardSlideAnimation()
					}

					composable("day3") {
						ReflectionAnimation()
					}

					composable("day4") {
						SweepLineAnimation()
					}

					composable("day5") {
						CircularRevealAnimation()
					}
				}
			}
		}
	}
}