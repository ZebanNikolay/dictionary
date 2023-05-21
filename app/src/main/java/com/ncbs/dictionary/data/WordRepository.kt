package com.ncbs.dictionary.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ncbs.dictionary.domain.LocaleData
import com.ncbs.dictionary.domain.Word
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.*

class WordRepository {

    companion object {
        private const val BASE_URL = "http://bibl-nogl-dictionary.ru"
    }

    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(
            OkHttpClient.Builder()
                .apply {
                    addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor
                                .Level.BODY
                        )
                    )
                }
                .build()
        )
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
                put(
                    "nv", LocaleData(
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