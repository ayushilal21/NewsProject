package com.example.newsapp

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItem>
)

data class NewsItem(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

data class Source(
    val id: String?,
    val name: String
)
