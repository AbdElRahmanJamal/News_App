package com.app.newsapp.framework.remote_data_source.breaking_news_remote_source

import com.app.core.data.breaking_news_data_source.BreakingNewsDataSource
import com.app.core.data.breaking_news_data_source.entity.NewsResponseModel
import com.app.newsapp.framework.remote_data_source.NetworkHandler
import javax.inject.Inject

class BreakingNewsRemoteDataSource @Inject constructor(
    private val networkHandler: NetworkHandler<NewsResponseModel>,
    private val breakingNewsApi: BreakingNewsApi
) : BreakingNewsDataSource {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ) = networkHandler.callAPI { breakingNewsApi.getBreakingNews(countryCode, pageNumber) }

}