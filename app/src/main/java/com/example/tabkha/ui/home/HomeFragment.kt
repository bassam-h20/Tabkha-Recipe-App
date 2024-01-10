package com.example.tabkha.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("DiscouragedApi")
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
        /////////////////////////////////
        //     Debug Image Id Here    //
        ////////////////////////////////

//        Log.d("#### IMAGE ID ####", getDrawableId("/* DRAWABLE NAME HERE */").toString())

        val recipes = parseJsonFile()
        adapter.submitList(recipes)
        return root
    }

    fun parseJsonFile(): List<Recipe> {
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


    @SuppressLint("DiscouragedApi")
    fun getDrawableId(drawableName: String): Int {
        return resources.getIdentifier(drawableName, "drawable", requireContext().packageName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//        recipes.add(Recipe(
//            "Koshari",
//            "Koshari is an Egyptian dish that is widely known in Arab countries. The iconic dish is a harmonious blend of ingredients weaving together a rich culinary heritage of Egypt.",
//            "Ingredients:\n" +
//                    "•\t115 g lentils\n" +
//                    "•\t300 g Rice\n" +
//                    "•\tMacaroni Pasta\n" +
//                    "•\t2 Onions \n" +
//                    "•\t80 g Flour \n" +
//                    "•\t4 Garlic cloves\n" +
//                    "•\t1 ½ tsp Ground Coriander\n" +
//                    "•\t1 can 800 g Tomato Sauce\n" +
//                    "•\t2 tablespoon White Vinegar\n" +
//                    "•\t½ tsp each salt and pepper\n" +
//                    "•\t½ - 1 tablespoon red pepper flakes (optional)",
//                    "For onion topping:\n" +
//                    "\n" +
//                    "1.  Grab one onion and cut into thin rings\n" +
//                    "2. Season them with salt\n" +
//                    "3. Coat the onion rings evenly with flour and make sure to shake off any excess\n" +
//                    "4. In a large pot on medium heat add cooking oil and fry the onion rings\n" +
//                    "5. Stir regulary and ensure the onions are crispy but not burned usually takes 15 – 20 minutes\n" +
//                    "\n" +
//                    "Tomato sauce:\n" +
//                    "\n" +
//                    "6. Heat one tablespoon of cooking oil and add a grated onion, cook until onion turns golden\n" +
//                    "7. Add minced garlic, coriander and red pepper flakes and sauté until fragrant\n" +
//                    "8. Introduce tomato sauce and pinch of salt and bring the mixture to a simmer for 15 minutes\n" +
//                    "9. Stir in the white vinegar, put heat on low and cover it \n" +
//                    "\nPrepare the koshari:\n" +
//                    "\n" +
//                    "10. Bring the rinsed lentils and 4 cups of water to a boil on high heat then reduce the heat to low allowing the lentils to cook until tender (15 minutes).\n" +
//                    "11. Drain the lentils and put aside\n" +
//                    "12. Soak the rice for 15 minutes in hot water then drain it\n" +
//                    "13. In a large pot drop the rice and lentils with 1 tablespoon cooking oil, salt, pepper and coriander, cook and stir regularly for 3 minutes\n" +
//                    "14. Add 3 cups of water to the rice and lentils and let boil on medium high heat for 5 -8 minutes\n" +
//                    "15. Cover the pot and allow it to cook for 20 minutes to assure the lentils and rice are fully cooked and put aside\n" +
//                    "16. Simultaneously boil the pasta until soft then drain\n" +
//                    "\n" +
//                    "Putting it together:\n" +
//                    "1.  Grab a large platter and place the rice and lentils at the base\n" +
//                    "2. Top with the drained pasta the tomato sauce \n" +
//                    "3. Finally garnish with the crispy onions",
//            R.drawable.recipe_img_koshari_drawable))

//        val koshari = Recipe("Test",
//            "Desc",
//            "Ing",
//            "Step",
//            getDrawable(R.drawable.recipe_img_koshari_drawable)
//        )
