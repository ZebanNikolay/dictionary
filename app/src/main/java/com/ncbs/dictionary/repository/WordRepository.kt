package com.ncbs.dictionary.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ncbs.dictionary.domain.LocaleData
import com.ncbs.dictionary.domain.Word
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.util.*

class WordRepository {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://bibl-nogl-dictionary.ru")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val service: WordService = retrofit.create(WordService:: class.java)

    suspend fun getWords(): List<Word> {
        return service.getWords().map {WordDto ->
            maptoWord(WordDto)
        }
    }

    fun maptoWord(wordDto: WordDto): Word {
        return Word(
            id = wordDto.id ?: "no id",
            locales = mapOf(
                "nv" to LocaleData(
                    locale = Locale("nv"),
                    value = wordDto.nv ?: "no nv"
                ),
                "ru" to LocaleData(
                    locale = Locale("ru"),
                    value = wordDto.ru ?: "no ru"
                ),
                "en" to LocaleData(
                    locale = Locale("en"),
                    value = wordDto.en ?: "no en"
                )
            )
        )

    }
}