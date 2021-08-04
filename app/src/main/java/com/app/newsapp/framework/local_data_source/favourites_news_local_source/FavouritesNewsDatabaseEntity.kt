package com.app.newsapp.framework.local_data_source.favourites_news_local_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "FavouritesNewsTable"
)
data class FavouritesNewsDatabaseEntity(
    @PrimaryKey(autoGenerate = true)
    val ID: Int? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)