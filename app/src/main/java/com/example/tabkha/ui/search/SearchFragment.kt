package com.example.tabkha.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tabkha.RecipeDetailActivity
import com.example.tabkha.databinding.FragmentSearchBinding
import com.example.tabkha.RecipeUtils
import com.example.tabkha.adapter.RecipeAdapter
import com.example.tabkha.model.Recipe

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.recyclerView
        // Initialize the adapter and set it to the RecyclerView
        val adapter = RecipeAdapter(requireContext()) { recipe ->
            RecipeUtils.addToFavorites(recipe, requireContext())
        }
        recyclerView.adapter = adapter

        // Access the SearchView
        val searchView = binding.searchView

        // Set the OnQueryTextListener for the SearchView
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val matchingRecipe = findRecipeByName(it)
                    if (matchingRecipe != null) {
                        val intent = Intent(requireContext(), RecipeDetailActivity::class.java).apply {
                            putExtra("recipe_name", matchingRecipe.name)
                            putExtra("recipe_description", matchingRecipe.description)
                            putExtra("recipe_ingredients", matchingRecipe.ingredients)
                            putExtra("recipe_steps", matchingRecipe.steps)
                            putExtra("image_res_id", matchingRecipe.imageResId)
                        }
                        startActivity(intent)
                    } else {
                        // Handle the case when no matching recipe is found
                        Toast.makeText(requireContext(), "No matching recipe found", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // You can perform incremental search here if needed
                return false
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun findRecipeByName(query: String): Recipe? {
        val allRecipes = RecipeUtils.parseJsonFile(resources)
        return allRecipes.find { recipe ->
            recipe.name.equals(query, ignoreCase = true)
        }
    }

}

//class SearchFragment : Fragment() {
//
//    //bassam
//    private lateinit var searchViewModel: SearchViewModel
//    private var _binding: FragmentSearchBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        //bassam
//        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
//        _binding = FragmentSearchBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        // Access the SearchView
//        val searchView = binding.searchView
//
//        //bassam
//        val recyclerView = binding.recyclerView
//        val adapter = RecipeAdapter(requireContext()) {recipe ->  /*Handle favorite click*/}
//        recyclerView.adapter = adapter
//        val recipes = RecipeUtils.parseJsonFile(resources)
//        adapter.submitList(recipes)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filterRecipes(newText.orEmpty())
//                return true
//            }
//        })
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
//
//private fun android.widget.SearchView.setOnQueryTextListener(onQueryTextListener: SearchView.OnQueryTextListener) {
//    TODO("Not yet implemented")
//}

//class SearchFragment : Fragment() {
//
//    private var _binding: FragmentSearchBinding? = null
//    private val binding get() = _binding!!
//
//    // Adapter for the RecyclerView
//    private lateinit var recipeAdapter: RecipeAdapter
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentSearchBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        // Access the SearchView
//        val searchView = binding.searchView
//
//        // Set up the RecyclerView and Adapter
//        recipeAdapter = RecipeAdapter(requireContext()) { recipe -> onFavoriteClick(recipe) }
//        binding.recyclerView.adapter = recipeAdapter
//
//        // Set up the SearchView listener using your extension function
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            android.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (!query.isNullOrBlank()) {
//                    val filteredRecipes = filterRecipesByQuery(query)
//                    val adapter = RecipeAdapter(requireContext()) { recipe ->
//                        // Handle the favorites button click
//                        RecipeUtils.addToFavorites(recipe, requireContext())
//                    }
//                    adapter.submitList(filteredRecipes)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Handle query text change
//                if (newText != null) {
//                    filterRecipes(newText)
//                }
//                return true
//            }
//        })
//
//        // Load recipes initially
//        val recipes = RecipeUtils.parseJsonFile(resources)
//        recipeAdapter.submitList(recipes)
//
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    // Function to handle favorite button click
//    private fun onFavoriteClick(recipe: Recipe) {
//        // Implement your logic here
//    }
//
//    // Function to filter recipes based on search query
//    private fun filterRecipes(query: String?) {
//        val filteredRecipes = recipeAdapter.getFilteredRecipes(query)
//        recipeAdapter.submitList(filteredRecipes)
//    }
//
//    private fun filterRecipesByQuery(query: String): List<Recipe> {
//        val allRecipes = RecipeUtils.parseJsonFile(resources)
//        return allRecipes.filter { recipe ->
//            // Filter logic based on the recipe's name or other criteria
//            recipe.name.contains(query, ignoreCase = true)
//        }
//    }
//
//}

//private fun android.widget.SearchView.setOnQueryTextListener(onQueryTextListener: SearchView.OnQueryTextListener) {
//    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//        override fun onQueryTextSubmit(query: String?): Boolean {
//            return onQueryTextListener.onQueryTextSubmit(query)
//        }
//
//        override fun onQueryTextChange(newText: String?): Boolean {
//            return onQueryTextListener.onQueryTextChange(newText)
//        }
//    })
//}


