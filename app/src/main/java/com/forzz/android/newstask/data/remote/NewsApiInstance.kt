package com.forzz.android.newstask.data.remote

import com.forzz.android.newstask.data.remote.dto.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object NewsApiInstance {
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val newsApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiService::class.java)

    fun getTopHeadlines(country: String, apiKey: String): List<Article> {
        val response = newsApiService.getTopHeadlines(country, apiKey).execute()

        if (!response.isSuccessful) {
            throw Exception("Request failed ${response.code()}, ${response.errorBody()?.string()}")
        }

        return response.body()?.articles ?: emptyList()
    }
}