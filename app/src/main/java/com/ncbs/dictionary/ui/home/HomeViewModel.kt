package com.ncbs.dictionary.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncbs.dictionary.data.WordRepository
import com.ncbs.dictionary.domain.Word
import com.ncbs.dictionary.domain.WordListItem
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

    private val currentLocale: MutableStateFlow<Locale> = MutableStateFlow(Locale("nv"))

    init {
        viewModelScope.launch {
            _words.value = repository.getWords()
        }
    }
}