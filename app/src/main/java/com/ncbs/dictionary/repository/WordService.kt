package com.ncbs.dictionary.repository

import retrofit2.http.GET

interface WordService {

    @GET("data/words.json")

    suspend fun getWords(): List<WordDto>
}