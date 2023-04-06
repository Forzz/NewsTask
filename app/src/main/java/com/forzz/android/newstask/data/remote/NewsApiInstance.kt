package com.forzz.android.newstask.data.remote

import com.forzz.android.newstask.data.remote.dto.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiInstance {
    private const val BASE_URL = "https://newsapi.org"

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val newsApi by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    suspend fun getTopHeadlines(country: String, apiKey: String): List<Article> {

        val news = newsApi.getTopHeadlines(country, apiKey)

        return news.articles
    }
}