package com.ncbs.dictionary.presentation

import com.ncbs.dictionary.domain.Word

data class WordListItem(
    val word: Word,
    val title: String
)