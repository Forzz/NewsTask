package com.forzz.android.newstask.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)