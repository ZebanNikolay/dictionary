package com.ncbs.dictionary.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ncbs.dictionary.databinding.FragmentFavoriteBinding
import com.ncbs.dictionary.ui.WordAdapter

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private val viewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding get() = _binding!!
    private val adapter: WordAdapter = WordAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}