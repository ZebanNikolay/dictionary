package com.ncbs.dictionary.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ncbs.dictionary.domain.LocaleData
import com.ncbs.dictionary.domain.Word
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.util.*

class WordRepository {

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://bibl-nogl-dictionary.ru")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val service: WordService = retrofit.create(WordService::class.java)

    suspend fun getWords(): List<Word> {
        return service.getWords().mapNotNull { WordDto ->
            mapToWord(WordDto)
        }
    }

    private fun mapToWord(wordDto: WordDto): Word? {
        return Word(
            id = wordDto.id ?: return null,
            locales = buildMap {
                put("nv", LocaleData(
                    locale = Locale("nv"),
                    value = wordDto.nv ?: return null
                )
                )
                wordDto.ru?.let {
                    "ru" to LocaleData(
                        locale = Locale("ru"),
                        value = it
                    )
                }
                wordDto.en?.let {
                    "ru" to LocaleData(
                        locale = Locale("en"),
                        value = it
                    )
                }
            }
        )
    }
}