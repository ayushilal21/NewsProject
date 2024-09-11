package com.example.newsapp.api

import com.example.newsapp.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {
    @GET("v2/top-headlines?country=us&apiKey=6d56940a5025440b9c63b7f628eb61a8")
    suspend fun getNews(): NewsResponse

    companion object {
        private const val BASE_URL = "https://newsapi.org/"

        val instance: NewsService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }

        suspend fun getNews(): NewsResponse {
            return instance.getNews()
        }
    }
}
