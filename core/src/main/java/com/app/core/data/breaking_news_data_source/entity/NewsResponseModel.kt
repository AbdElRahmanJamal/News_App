package com.app.core.data.breaking_news_data_source.entity

data class NewsResponseModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)