package com.example.razmik_hw3.DataClasses

import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?,
    @SerializedName("articles")
    var articles: List<ArticleResponse>?
)

class ArticleResponse(
    @SerializedName("source")
    var source: SourceResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("description")
    val description: String?
    )

class SourceResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    var name: String?,

)
