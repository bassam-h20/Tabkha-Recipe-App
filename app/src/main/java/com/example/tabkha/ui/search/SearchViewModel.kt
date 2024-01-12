package com.example.tabkha.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tabkha.model.Recipe

class SearchViewModel : ViewModel() {
    private val _allRecipes = MutableLiveData<List<Recipe>>()
    val allRecipes: LiveData<List<Recipe>> get() = _allRecipes

    fun setRecipes(recipes: List<Recipe>) {
        _allRecipes.value = recipes
    }
}