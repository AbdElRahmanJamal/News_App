package com.app.newsapp.framework.local_data_source.favourites_news_local_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(favouritesNewsDatabaseEntity: FavouritesNewsDatabaseEntity)

    @Query("select * from FavouritesNewsTable")
    fun getAllFavouritesNews(): Flow<List<FavouritesNewsDatabaseEntity>>

    @Delete
    suspend fun removeNewsFromFavouritesNews(favouritesNewsDatabaseEntity: FavouritesNewsDatabaseEntity)
}