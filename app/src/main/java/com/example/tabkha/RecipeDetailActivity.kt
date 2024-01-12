package com.example.tabkha

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tabkha.databinding.ActivityRecipeDetailBinding

// Import necessary packages

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from intent or ViewModel
        val recipeName = intent.getStringExtra("recipe_name") ?: "Recipe Name"
        val recipeDescription = intent.getStringExtra("recipe_description") ?: "Recipe Description"
        val recipeIngredients = intent.getStringExtra("recipe_ingredients") ?: "Recipe Ingredients"
        val recipeSteps = intent.getStringExtra("recipe_steps") ?: "Recipe Steps"
//        val imageDrawableName = intent.getStringExtra("image_drawable_name") ?: "recipe_img_koshari_drawable"
        val imageDrawableName = intent.getStringExtra("image_drawable_name") ?: "adas"
        val recipeCountry = intent.getStringExtra("recipe_country") ?: "Country Name"
        val imageResId = resources.getIdentifier(imageDrawableName, "drawable", packageName)

// Set data to views
        binding.imageRecipeDetail.setImageResource(imageResId)
        binding.textRecipeNameDetail.text = recipeName
        binding.textRecipeDescription.text = recipeDescription
        binding.textRecipeIngredients.text = recipeIngredients
        binding.textRecipeSteps.text = recipeSteps
        binding.textRecipeCountry.text = recipeCountry

        val btnFavorite: ImageButton = binding.btnFavorite

        // Load favorites from SharedPreferences
        val preferences: SharedPreferences = getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        val favoritesSet: MutableSet<String> = preferences.getStringSet("favoritesSet", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Set favorite icon based on the current status
        if (isFavorite(recipeName, favoritesSet)) {
            btnFavorite.setImageResource(R.drawable.ic_favorite_red_filled_24dp)
        } else {
            btnFavorite.setImageResource(R.drawable.ic_favorite_24dp)
        }

        btnFavorite.setOnClickListener {
            // Toggle the favorite status
            if (isFavorite(recipeName, favoritesSet)) {
                favoritesSet.remove(recipeName)
                btnFavorite.setImageResource(R.drawable.ic_favorite_24dp)
            } else {
                favoritesSet.add(recipeName)
                btnFavorite.setImageResource(R.drawable.ic_favorite_red_filled_24dp)
            }

            // Save the updated favorites to SharedPreferences
            preferences.edit().putStringSet("favoritesSet", favoritesSet).apply()
        }
    }

    private fun isFavorite(recipeName: String, favoritesSet: Set<String>): Boolean {
        return favoritesSet.contains(recipeName)
    }
}