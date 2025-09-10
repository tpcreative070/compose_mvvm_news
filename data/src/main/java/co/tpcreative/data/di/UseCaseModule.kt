package co.tpcreative.data.di

import co.tpcreative.domain.repository.FavoriteRepository
import co.tpcreative.domain.repository.NewsRepository
import co.tpcreative.domain.repository.SourceRepository
import co.tpcreative.domain.usecase.GetNewsBySourceUseCase
import co.tpcreative.domain.usecase.GetSourcesUseCase
import co.tpcreative.domain.usecase.GetTopHeadlinesUseCase
import co.tpcreative.domain.usecase.ManageNewsFavoriteUseCase
import co.tpcreative.domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetTopHeadlinesUseCase(repo: NewsRepository): GetTopHeadlinesUseCase =
        GetTopHeadlinesUseCase(repo)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(repo: NewsRepository): SearchNewsUseCase =
        SearchNewsUseCase(repo)

    @Provides
    @Singleton
    fun provideManageNewsFavoriteUseCase(favRepo: FavoriteRepository): ManageNewsFavoriteUseCase =
        ManageNewsFavoriteUseCase(favRepo)

    @Provides
    @Singleton
    fun provideGetSourcesUseCase(repo: SourceRepository): GetSourcesUseCase =
        GetSourcesUseCase(repo)

    @Provides
    @Singleton
    fun provideGetNewsBySourceUseCase(repo: NewsRepository): GetNewsBySourceUseCase =
        GetNewsBySourceUseCase(repo)
}