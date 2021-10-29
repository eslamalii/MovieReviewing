package com.example.movieapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentTabsBinding
import com.example.movieapp.ui.main.SectionsPagerAdapter
import com.example.movieapp.ui.main.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator

class ItemTabsFragment : Fragment(R.layout.fragment_tabs) {

    private var _binding: FragmentTabsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTabsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SectionsPagerAdapter(requireActivity())

        binding.viewpager.adapter = adapter
        binding.viewpager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = String.format(getString(TAB_TITLES[position]))
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}