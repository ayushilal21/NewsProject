package com.example.newsapp.newDesign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.NewsItem
import com.example.newsapp.NewsResponse
import com.example.newsapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class NewsListPage : AppCompatActivity(), OnItemClickListener {

    private lateinit var newsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list_page)

        newsRecyclerView = findViewById(R.id.newsRecyclerView) // Initialize RecyclerView
        val newsItems = loadNewsData()
        setupRecyclerView(newsItems)
    }

    private fun loadNewsData(): List<NewsItem> {
        val jsonString = assets.open("news_data.json").bufferedReader().use { it.readText() }
        val newsResponseType: Type = object : TypeToken<NewsResponse>() {}.type
        val newsResponse: NewsResponse = Gson().fromJson(jsonString, newsResponseType)
        return newsResponse.articles
    }

    private fun setupRecyclerView(newsItems: List<NewsItem>) {
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.adapter = NewsAdapter(newsItems,this)
    }

    override fun onItemClick(newsItem: NewsItem) {
        val gson = Gson()
        val newsItemString = gson.toJson(newsItem)
        val intent = Intent(this, NewsDetailsPage::class.java)
        intent.putExtra("newsItem", newsItemString)
        startActivity(intent)
    }
}