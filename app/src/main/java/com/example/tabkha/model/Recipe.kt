package com.example.tabkha.model

data class Recipe(
    var name: String,
    val description: String,
    val ingredients: String,
    val steps: String,
    var imageResId: Int // Add this property for image resource ID
)
