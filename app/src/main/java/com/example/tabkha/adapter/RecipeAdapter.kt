package com.example.tabkha.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkha.RecipeDetailActivity
import com.example.tabkha.databinding.ItemRecipeBinding
import com.example.tabkha.model.Recipe
import com.example.tabkha.R

class RecipeAdapter(private val context: Context, private val onFavoriteClick: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipes: List<Recipe> = emptyList()
    val favoritesSet: MutableSet<String> = mutableSetOf()

    init {
        // Load favorites from SharedPreferences
        val preferences: SharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        favoritesSet.addAll(preferences.getStringSet("favoritesSet", mutableSetOf()) ?: emptySet())
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Recipe>) {
        recipes = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Set click listeners for image, text, and favorite button
            binding.imageRecipe.setOnClickListener { onImageClick() }
            binding.textRecipeName.setOnClickListener { onTextNameClick() }
            binding.btnFavorite.setOnClickListener { onFavoriteClick() }
        }

        fun bind(recipe: Recipe) {
            binding.imageRecipe.setImageResource(recipe.imageResId)
            binding.textRecipeName.text = recipe.name

            // Set favorite icon based on the current status
            if (isFavorite(recipe)) {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_red_filled_24dp)
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_24dp)
            }
        }

        private fun onImageClick() {
            navigateToRecipeDetailPage(adapterPosition)
        }

        private fun onTextNameClick() {
            navigateToRecipeDetailPage(adapterPosition)
        }

        private fun onFavoriteClick() {
            val recipe = recipes[adapterPosition]

            // Toggle the favorite status
            if (isFavorite(recipe)) {
                favoritesSet.remove(recipe.name)
            } else {
                favoritesSet.add(recipe.name)
            }

            // Save the updated favorites to SharedPreferences
            val preferences: SharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
            preferences.edit().putStringSet("favoritesSet", favoritesSet).apply()

            // Update the favorite icon
            notifyItemChanged(adapterPosition)
        }

        private fun navigateToRecipeDetailPage(position: Int) {
            val context = binding.root.context
            val recipe = recipes[position]

            // Create intent to start RecipeDetailActivity
            val intent = Intent(context, RecipeDetailActivity::class.java).apply {
                putExtra("recipe_name", recipe.name)
                putExtra("recipe_description", recipe.description)
                putExtra("recipe_ingredients", recipe.ingredients)
                putExtra("recipe_steps", recipe.steps)
                putExtra("image_res_id", recipe.imageResId)
            }

            // Start the activity
            context.startActivity(intent)
        }

        fun isFavorite(recipe: Recipe): Boolean {
            return favoritesSet.contains(recipe.name)
        }
    }
}
