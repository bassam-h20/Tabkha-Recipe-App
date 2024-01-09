package com.example.tabkha.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SwitchCompat
import com.example.tabkha.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        switchDarkMode = binding.switchDarkMode
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Load the dark mode state from SharedPreferences
        switchDarkMode.isChecked = sharedPreferences.getBoolean(DARK_MODE_KEY, false)

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Save the dark mode state to SharedPreferences
            with(sharedPreferences.edit()) {
                putBoolean(DARK_MODE_KEY, isChecked)
                apply()
            }

            // Apply dark mode to the app
            applyDarkMode(isChecked)
        }

        return root
    }

    private fun applyDarkMode(isDarkMode: Boolean) {
         if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
         } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
         }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DARK_MODE_KEY = "dark_mode"
    }
}
