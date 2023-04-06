package com.forzz.android.newstask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.forzz.android.newstask.data.model.ArticleItem
import com.forzz.android.newstask.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val news: MutableList<ArticleItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).onBind(getItem(position))
    }

    override fun getItemCount(): Int = news.size

    private fun getItem(position: Int): ArticleItem = news[position]

    inner class NewsViewHolder(
        private val binding: NewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(article: ArticleItem) {
            Glide.with(binding.root.context)
                .load(article.imageUrl)
                .transform(RoundedCorners(12))
                .into(binding.ivNewsPreview)

            binding.tvNewsTitle.text = article.title
            binding.tvNewsDescription.text = article.description
            binding.tvSourceTitle.text = article.source
        }
    }

    fun addData(list: List<ArticleItem>) {
        this.news.clear()
        this.news.addAll(list)
        notifyDataSetChanged()
    }
}