package com.app.core.data.breaking_news_data_source

import com.app.core.data.breaking_news_data_source.entity.NewsResponseModel
import com.app.core.domain.util.APIState
import kotlinx.coroutines.flow.Flow


interface BreakingNewsDataSource {

    suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int,
    ): Flow<APIState<NewsResponseModel>>

}