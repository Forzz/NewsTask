package com.forzz.android.newstask.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forzz.android.newstask.data.model.ArticleItem
import com.forzz.android.newstask.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {

    var apiKey = MutableLiveData<String>()
    val newsRepository = NewsRepository("63978f286fc145e5a0e0b05105baeb45")

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    fun getNews(country: String) {
        viewModelScope.launch {
            val response = newsRepository.getTopHeadlines(country)
            _articles.postValue(response)
        }
    }
}