package com.forzz.android.newstask.data.remote

import com.forzz.android.newstask.data.remote.dto.NewsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsDTO>
}