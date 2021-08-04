package com.app.newsapp.framework.remote_data_source.breaking_news_remote_source

import com.app.core.data.breaking_news_data_source.entity.NewsResponseModel
import com.app.core.domain.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingNewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String,
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponseModel>
}