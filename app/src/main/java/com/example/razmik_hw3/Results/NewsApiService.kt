package com.example.razmik_hw3.Results

import com.example.razmik_hw3.DataClasses.NewsData
import com.example.razmik_hw3.newsResources.News
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsApiService {

//    @GET("/v2/top-headlines?country={country}?&apiKey=35809363b23d4e5b8364338193488151")
//        fun fetchNews(@Query("country") country: String?): newsData
    @GET("/v2/top-headlines?country=us&apiKey=35809363b23d4e5b8364338193488151")
    suspend fun getNews(
        @Query("category") category: String = "",
        @Query("q") q: String = ""
    ): NewsData
}