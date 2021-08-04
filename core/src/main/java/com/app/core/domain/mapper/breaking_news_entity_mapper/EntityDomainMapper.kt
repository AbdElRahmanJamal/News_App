package com.app.core.domain.mapper.breaking_news_entity_mapper

import com.app.core.data.EntityMapper
import com.app.core.data.breaking_news_data_source.entity.Article
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModel
import javax.inject.Inject

class EntityDomainMapper @Inject constructor() :
    EntityMapper<Article, BreakingNewsDomainModel> {

    override fun convertFromEntityToDomain(
        entity: Article,
    ) = BreakingNewsDomainModel(
        author = entity.author,
        content = entity.content,
        description = entity.description,
        publishedAt = entity.publishedAt,
        title = entity.title,
        url = entity.url,
        urlToImage = entity.urlToImage
    )


    override fun convertFromDomainToEntity(
        domain: BreakingNewsDomainModel
    ) = Article(
        author = domain.author,
        content = domain.content,
        description = domain.description,
        publishedAt = domain.publishedAt,
        title = domain.title,
        url = domain.url,
        urlToImage = domain.urlToImage,
        source = null
    )
}