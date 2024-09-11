package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newsapp.api.NewsService
import kotlinx.coroutines.launch

class NewsScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsScreen()
        }
    }
}

@Composable
fun NewsScreen() {
    val newsList = remember { mutableStateListOf<NewsItem>() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val newsResponse = NewsService.getNews() // Fetch the news
                newsList.addAll(newsResponse.articles)   // Extract articles and add to list
            } catch (e: Exception) {
                Log.e("NewsScreen", "Error fetching news", e)
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(newsList.size) { index ->
            NewsCard(newsItem = newsList[index])
        }
    }
}
