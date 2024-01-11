package com.example.tabkha.ui.favorites

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkha.RecipeUtils
import com.example.tabkha.adapter.RecipeAdapter
import com.example.tabkha.databinding.FragmentFavoritesBinding
import androidx.recyclerview.widget.GridLayoutManager

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favoritesViewModel =
            ViewModelProvider(this)[FavoritesViewModel::class.java]

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerViewFavorites

        return root
    }

    private fun loadFavorites() {
        val preferences: SharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        val favoritesSet: Set<String> = preferences.getStringSet("favoritesSet", emptySet()) ?: emptySet()

        val recipes = RecipeUtils.parseJsonFile(resources) // Replace this with your actual data source
        val favoriteRecipes = recipes.filter { it.name in favoritesSet }

        adapter.submitList(favoriteRecipes)
    }

    // ...

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your RecyclerView adapter and layout manager
        adapter = RecipeAdapter(requireContext()) { recipe ->
            // Handle the favorites button click
            RecipeUtils.addToFavorites(recipe, requireContext())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Load and display favorite recipes
        loadFavorites()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}