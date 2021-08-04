package com.app.core.domain.interactors.breaking_news_interactors

import com.app.core.data.EntityMapper
import com.app.core.data.breaking_news_data_source.BreakingNewsRepository
import com.app.core.data.breaking_news_data_source.entity.Article
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModel
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModelList
import com.app.core.domain.util.APIState
import com.app.core.domain.util.DIQualifier
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreakingNewsByCountryAndPageNumberUseCase
@Inject constructor(
    private val breakingNewsRepository: BreakingNewsRepository,
    @DIQualifier.QualifierEntityDomainMapper
    private val domainEntityMapper: EntityMapper<Article, BreakingNewsDomainModel>
) {

    suspend fun getBreakingNewsByCountryAndPageNumber(countryCode: String, pageNumber: Int) = flow {
        breakingNewsRepository.getBreakingNews(countryCode, pageNumber).collect {
            when (it) {
                is APIState.LoadingState -> emit(it)
                is APIState.DataStat ->
                    emit(APIState.DataStat(listOfArticleToListOfBreakingNewsDomainModel(it.value.articles)))
                is APIState.ErrorState -> emit(it)
            }
        }
    }

    private fun listOfArticleToListOfBreakingNewsDomainModel(listOfArticle: List<Article>): BreakingNewsDomainModelList {
        val listOfDomainModel = listOfArticle.map {
            domainEntityMapper.convertFromEntityToDomain(it)
        }
        return BreakingNewsDomainModelList(listOfDomainModel)
    }

}