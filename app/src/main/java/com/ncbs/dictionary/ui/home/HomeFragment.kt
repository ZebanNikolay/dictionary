package com.ncbs.dictionary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncbs.dictionary.databinding.FragmentHomeBinding
import com.ncbs.dictionary.ui.WordAdapter
import com.ncbs.dictionary.ui.WordDetailsBottomSheet
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val adapter: WordAdapter = WordAdapter()

    private var wordDetailsBottomSheet:WordDetailsBottomSheet? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.listItemWord.adapter = adapter
        binding.listItemWord.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.words.collect { words ->
                binding.progressBar.isVisible = words.isEmpty()
                binding.listItemWord.isVisible = words.isNotEmpty()
                adapter.submitData(words)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}