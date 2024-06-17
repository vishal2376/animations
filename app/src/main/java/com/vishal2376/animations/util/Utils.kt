package com.vishal2376.animations.util

import androidx.compose.ui.graphics.Color
import com.vishal2376.animations.presentation.common.Contact
import kotlin.random.Random

val contactList = generateRandomContacts(100)

fun generateRandomContacts(count: Int): List<Contact> {
	val names =
		listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hannah", "Ivy", "Jack")
	val contacts = mutableListOf<Contact>()

	repeat(count) {
		val name = names[Random.nextInt(names.size)]
		val number = "123-2376-${Random.nextInt(1000, 9999)}"
		contacts.add(Contact(name, number))
	}

	return contacts
}

fun generateContrastingColors(): Pair<Color, Color> {
	val hue = Random.nextInt(0, 360)
	val saturation = 0.8f
	val value = 0.8f

	val backgroundColor = Color.hsv(hue.toFloat(), saturation, value)
	val textColor = Color.hsv(hue.toFloat(), 0.5f, 1.0f)

	return Pair(backgroundColor, textColor)
}

