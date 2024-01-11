package com.example.tabkha

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import com.example.tabkha.model.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object RecipeUtils {
    fun addToFavorites(recipe: Recipe, context: Context) {
        val favoritesSet: MutableSet<String> = mutableSetOf()

        // Load existing favorites from SharedPreferences
        val preferences: SharedPreferences =
            context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        favoritesSet.addAll(preferences.getStringSet("favoritesSet", mutableSetOf()) ?: emptySet())

        // Add the recipe to favorites
        favoritesSet.add(recipe.name)

        // Save the updated favorites to SharedPreferences
        preferences.edit().putStringSet("favoritesSet", favoritesSet).apply()

        // Implement any other logic based on your requirements
        // For example, update the UI or trigger relevant actions
    }


    fun parseJsonFile(resources: Resources): List<Recipe> {
        return try {
            val inputStream = resources.openRawResource(R.raw.recipes)
            val jsonContent = inputStream.bufferedReader().use { it.readText() }

            val type = object : TypeToken<List<Recipe>>() {}.type
            val recipes = Gson().fromJson<List<Recipe>>(jsonContent, type)

            Log.d("JSON", "Parsed recipes: $recipes")
            recipes
        } catch (e: Exception) {
            // Handle the exception (e.g., log or show an error message)
            Log.e("JSON", "Error parsing JSON: $e")
            emptyList()
        }
    }
}