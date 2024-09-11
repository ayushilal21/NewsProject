package com.example.newsapp.newDesign

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.NewsItem
import com.example.newsapp.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class NewsDetailsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details_page)

//        var actionBar = getSupportActionBar()
//
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true)
//        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val newsItemString = intent.getStringExtra("newsItem")
        val gson = Gson()
        val result = gson.fromJson(newsItemString, NewsItem::class.java)

        val newsTitle = findViewById<TextView>(R.id.newsTitle)
        val newsDescription = findViewById<TextView>(R.id.newsDescription)
        val newsImage = findViewById<ImageView>(R.id.newsImage)

        newsTitle.text = result.title
        newsDescription.text = result.description
        if(result.urlToImage != null){
            Picasso.get().load(result.urlToImage.toString()).placeholder(R.drawable.default_image).into(newsImage)
        } else {
            Picasso.get().load(R.drawable.default_image).placeholder(R.drawable.default_image).into(newsImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
