package com.app.core.domain.entities.breaking_news_entity

data class BreakingNewsDomainModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)

data class BreakingNewsDomainModelList(
    val breakingNewsDomainModelList: List<BreakingNewsDomainModel> = emptyList()
)

