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

        // Customize the SearchView based on theme mode
        val uiModeManager = requireContext().getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
        if (uiModeManager.nightMode == Configuration.UI_MODE_NIGHT_YES) {
            // Dark mode is active, set dark mode drawable
            searchView.setBackgroundResource(R.drawable.night_round_searchview)
            searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)?.setTextColor(
                ContextCompat.getColor(requireContext(), android.R.color.white)
            )
        } else {
            // Light mode is active, set light mode drawable
            searchView.setBackgroundResource(R.drawable.round_searchview)
        }

//        searchView.clearFocus()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
