package com.forzz.android.newstask.data.remote.dto

data class NewsDTO(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)