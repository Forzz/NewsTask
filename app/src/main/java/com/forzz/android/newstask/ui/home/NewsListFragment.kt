package com.forzz.android.newstask.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.forzz.android.newstask.R
import com.forzz.android.newstask.data.model.ArticleItem
import com.forzz.android.newstask.databinding.NewsListFragmentBinding
import kotlinx.coroutines.launch

class NewsListFragment : Fragment() {

    private lateinit var binding: NewsListFragmentBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var apiKey: String
    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        /*
            Bad way to store api keys, but better than hardcode.
            API-ключ хранится в ресурсах, но лучше прописать его в gradle.properties, и далее
            добавить в build.gradle - buildConfigField, в котором указать название переменной ключа.
        */
        adapter = NewsAdapter()
        apiKey = resources.getString(R.string.news_api_key)
        viewModel.apiKey.value = apiKey
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container, false)
        binding.newsListViewModel = viewModel
        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = adapter

        viewModel.articles.observe(viewLifecycleOwner) { response ->
            response?.let {
                initRecyclerView(response)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getNews("us")
    }

    private fun initRecyclerView(news: List<ArticleItem>) {
        adapter.addData(news)
    }

}