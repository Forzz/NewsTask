package com.forzz.android.newstask.data.remote.dto

import com.forzz.android.newstask.data.model.ArticleItem

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    fun toArticle(): ArticleItem {
        return ArticleItem(
            title = title,
            source = source?.name,
            description = description,
            imageUrl = urlToImage
        )
    }
}