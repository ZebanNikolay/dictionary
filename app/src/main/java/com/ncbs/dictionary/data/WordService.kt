package com.ncbs.dictionary.data

import retrofit2.http.GET

interface WordService {

    @GET("data/words.json")
    suspend fun getWords(): List<WordDto>
}