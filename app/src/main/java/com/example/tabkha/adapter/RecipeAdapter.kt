package com.example.tabkha.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkha.RecipeDetailActivity
import com.example.tabkha.databinding.ItemRecipeBinding
import com.example.tabkha.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var recipes: List<Recipe> = emptyList()

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
            // Set other data as needed
        }

        private fun onImageClick() {
            navigateToRecipeDetailPage(adapterPosition)
        }

        private fun onTextNameClick() {
            navigateToRecipeDetailPage(adapterPosition)
        }

        private fun onFavoriteClick() {
            // Handle click on favorite button
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
    }
}
