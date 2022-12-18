package com.ncbs.dictionary.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.ncbs.dictionary.presentation.WordListItem
import com.ncbs.dictionary.repository.WordDto
import com.ncbs.dictionary.repository.WordRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {

    private val _words: MutableStateFlow<List<Word>> = MutableStateFlow(emptyList())

    val words: StateFlow<List<WordListItem>> = _words.map { words ->
        words.mapNotNull { word ->
            WordListItem(
                word = word,
                title = word.locales[currentLocale.value.language]?.value ?: return@mapNotNull null
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val repository = WordRepository()

    private val currentLocale: MutableStateFlow<Locale> = MutableStateFlow(Locale("ru"))

    init {

        viewModelScope.launch {
            _words.value = repository.getWords()

        }
    }


}
