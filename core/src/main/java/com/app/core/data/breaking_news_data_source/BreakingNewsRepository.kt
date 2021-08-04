package com.app.core.data.breaking_news_data_source

import com.app.core.domain.util.DIQualifier
import javax.inject.Inject

class BreakingNewsRepository @Inject constructor(
    @DIQualifier.QualifierBreakingNewsRemoteDataSource
    private val breakingNewsDataSource: BreakingNewsDataSource
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        breakingNewsDataSource.getBreakingNews(countryCode, pageNumber)
}