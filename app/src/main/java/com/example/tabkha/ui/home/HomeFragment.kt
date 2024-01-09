package com.example.tabkha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tabkha.R
import com.example.tabkha.adapter.RecipeAdapter
import com.example.tabkha.databinding.FragmentHomeBinding
import com.example.tabkha.model.Recipe

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        val adapter = RecipeAdapter() // Create your custom adapter
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Set sample data for testing
        val recipes = mutableListOf<Recipe>() // Replace with your actual data
        for (i in 1..10) {
            recipes.add(Recipe("Recipe $i", R.drawable.recipe_img_koshari_drawable))
        }

        adapter.submitList(recipes)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
