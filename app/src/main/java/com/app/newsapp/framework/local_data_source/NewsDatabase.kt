package com.app.newsapp.framework.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.newsapp.framework.local_data_source.favourites_news_local_source.FavouritesNewsDao
import com.app.newsapp.framework.local_data_source.favourites_news_local_source.FavouritesNewsDatabaseEntity

@Database(entities = [FavouritesNewsDatabaseEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun favouritesNewsDaoDao(): FavouritesNewsDao

}