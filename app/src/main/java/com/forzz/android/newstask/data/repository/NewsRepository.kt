package com.forzz.android.newstask.data.repository

import android.util.Log
import com.forzz.android.newstask.data.model.ArticleItem
import com.forzz.android.newstask.data.remote.NewsApiInstance

class NewsRepository(private val apiKey: String) {

    suspend fun getTopHeadlines(country: String): List<ArticleItem> {
        return try {
            val response = NewsApiInstance.getTopHeadlines(country, apiKey)
            response.map { articleDto -> articleDto.toArticle() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}