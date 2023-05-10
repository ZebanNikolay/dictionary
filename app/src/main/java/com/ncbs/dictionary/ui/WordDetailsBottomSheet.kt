package com.ncbs.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncbs.dictionary.databinding.WordDetailsBottomsheetBinding

class WordDetailsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: WordDetailsBottomsheetBinding? = null
    private val binding: WordDetailsBottomsheetBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = WordDetailsBottomsheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}