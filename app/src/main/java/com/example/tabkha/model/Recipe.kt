package com.example.tabkha.model

data class Recipe(
    var name: String,
    val description: String,
    val ingredients: String,
    val steps: String,
    var imageResId: String,
    val country: String
)
