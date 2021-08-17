package com.app.newsapp.framework.di.breaking_news_di

import com.app.core.data.EntityMapper
import com.app.core.data.breaking_news_data_source.BreakingNewsDataSource
import com.app.core.data.breaking_news_data_source.entity.Article
import com.app.core.data.breaking_news_data_source.entity.NewsResponseModel
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModel
import com.app.core.domain.mapper.breaking_news_entity_mapper.EntityDomainMapper
import com.app.core.domain.util.DIQualifier
import com.app.core.domain.util.DispatcherProvider
import com.app.core.domain.util.StandardDispatcher
import com.app.newsapp.framework.remote_data_source.NetworkHandler
import com.app.newsapp.framework.remote_data_source.breaking_news_remote_source.BreakingNewsApi
import com.app.newsapp.framework.remote_data_source.breaking_news_remote_source.BreakingNewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object BreakingNewsModule {

    @Provides
    @ViewModelScoped
    fun providesStandardDispatcher(): DispatcherProvider = StandardDispatcher()

    @Provides
    @ViewModelScoped
    fun providesNetworkHandlerForBreakingNewsRemoteDataSource(ioDispatcher: CoroutineDispatcher)
            : NetworkHandler<NewsResponseModel> = NetworkHandler(ioDispatcher)

    @Provides
    @ViewModelScoped
    fun providesBreakingNewsApi(retrofit: Retrofit): BreakingNewsApi =
        retrofit.create(BreakingNewsApi::class.java)

    @DIQualifier.QualifierEntityDomainMapper
    @Provides
    @ViewModelScoped
    fun providesDomainEntityMapper(): EntityMapper<Article, BreakingNewsDomainModel> =
        EntityDomainMapper()

    @DIQualifier.QualifierBreakingNewsRemoteDataSource
    @Provides
    @ViewModelScoped
    fun providesBreakingNewsRemoteDataSource(
        networkHandler: NetworkHandler<NewsResponseModel>,
        breakingNewsApi: BreakingNewsApi,
    ): BreakingNewsDataSource =
        BreakingNewsRemoteDataSource(networkHandler, breakingNewsApi)
}
