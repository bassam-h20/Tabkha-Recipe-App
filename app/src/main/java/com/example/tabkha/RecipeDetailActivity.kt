package com.example.tabkha

import android.app.Activity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.tabkha.databinding.ActivityRecipeDetailBinding
import com.example.tabkha.ui.settings.SettingsFragment

// Import necessary packages

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data from intent or ViewModel
        val recipeName = intent.getStringExtra("recipe_name") ?: "Recipe Name"
        val recipeDescription = intent.getStringExtra("recipe_description") ?: "Recipe Description"
        val recipeIngredients = intent.getStringExtra("recipe_ingredients") ?: "Recipe Ingredients"
        val recipeSteps = intent.getStringExtra("recipe_steps") ?: "Recipe Steps"
        val imageResId = intent.getIntExtra("image_res_id", R.drawable.recipe_img_koshari_drawable)

        // Set data to views
        binding.imageRecipeDetail.setImageResource(imageResId)
        binding.textRecipeNameDetail.text = recipeName
        binding.textRecipeDescription.text = recipeDescription
        binding.textRecipeIngredients.text = recipeIngredients
        binding.textRecipeSteps.text = recipeSteps

        val btnFavorite: ImageButton = findViewById(R.id.btnFavorite)

        binding.btnFavorite.setOnClickListener { /* Handle click */ }
    }
}

