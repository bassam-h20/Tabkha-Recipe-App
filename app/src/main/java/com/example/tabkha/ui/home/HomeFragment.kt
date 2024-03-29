package com.example.tabkha.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tabkha.RecipeUtils
import com.example.tabkha.adapter.RecipeAdapter
import com.example.tabkha.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

//  1  private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        val adapter = RecipeAdapter(requireContext()) { recipe ->
            // Handle the favorites button click
            RecipeUtils.addToFavorites(recipe, requireContext())
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val recipes = RecipeUtils.parseJsonFile(resources)
        adapter.submitList(recipes)

        Log.d("Koshari", getDrawableId("koshari").toString())
        Log.d("Tabbouleh", getDrawableId("tabbouleh").toString())
        Log.d("Arayes", getDrawableId("arayes").toString())
        Log.d("Adas", getDrawableId("adas").toString())
        Log.d("Majboos", getDrawableId("majboos").toString())


        return root
    }


    @SuppressLint("DiscouragedApi")
    fun getDrawableId(drawableName: String): Int {
        return resources.getIdentifier(drawableName, "drawable", requireContext().packageName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}