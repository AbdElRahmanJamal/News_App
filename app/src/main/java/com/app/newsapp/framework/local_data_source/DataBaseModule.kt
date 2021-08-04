package com.app.newsapp.framework.local_data_source

import android.content.Context
import androidx.room.Room
import com.app.core.domain.util.Constants
import com.app.newsapp.framework.local_data_source.favourites_news_local_source.FavouritesNewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun providesFavouritesNewsDaoDao(newsDatabase: NewsDatabase): FavouritesNewsDao =
        newsDatabase.favouritesNewsDaoDao()

    @Provides
    @Singleton
    fun providesNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, Constants.DATA_BASE_NAME).build()

}