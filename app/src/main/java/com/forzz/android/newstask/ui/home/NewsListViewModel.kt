package com.forzz.android.newstask.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forzz.android.newstask.BuildConfig
import com.forzz.android.newstask.data.model.ArticleItem
import com.forzz.android.newstask.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {

    val newsRepository = NewsRepository(BuildConfig.NEWS_API_KEY)


    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    fun getNews(country: String) {
        viewModelScope.launch {
            val response = newsRepository.getTopHeadlines(country)
            _articles.postValue(response)
        }
    }
}