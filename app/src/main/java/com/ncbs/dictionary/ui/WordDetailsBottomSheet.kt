package com.ncbs.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncbs.dictionary.databinding.WordDetailsBottomsheetBinding
import com.ncbs.dictionary.domain.Word
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

class WordDetailsBottomSheet(
    private val word: Word
) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "WordDetailsBottomSheet"
    }

    private var _binding: WordDetailsBottomsheetBinding? = null
    private val binding: WordDetailsBottomsheetBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = WordDetailsBottomsheetBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleScope.launch {
                nvWord.text = "Nivh word"
                ruWord.text = "Russian word"
                enWord.text = "English word"
            }
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}