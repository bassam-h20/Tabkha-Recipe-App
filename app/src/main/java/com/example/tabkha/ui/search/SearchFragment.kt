package com.example.tabkha.ui.search

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tabkha.R
import com.example.tabkha.databinding.FragmentSearchBinding
import android.util.Log
import com.example.tabkha.ui.settings.SettingsFragment

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

        // Access the SearchView
        val searchView = binding.searchView

        // Retrieve dark mode status from shared preferences
        val isDarkMode = getDarkModeStatus()

        // Customize the SearchView based on theme mode
        if (isDarkMode) {
            // Dark mode is active, set dark mode drawable
            searchView.setBackgroundResource(R.drawable.night_round_searchview)
        } else {
            // Light mode is active, set light mode drawable
            searchView.setBackgroundResource(R.drawable.round_searchview)
        }

        return root
    }

    private fun getDarkModeStatus(): Boolean {
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(SettingsFragment.DARK_MODE_KEY, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
