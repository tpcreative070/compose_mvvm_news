package co.tpcreative.data.di

import co.tpcreative.core.util.PagingConstants
import co.tpcreative.data.BuildConfig
import co.tpcreative.data.database.NewsDao
import co.tpcreative.data.database.NewsDatabase
import co.tpcreative.data.network.NewsApi
import co.tpcreative.data.database.SourceDao
import co.tpcreative.domain.repository.FavoriteRepository
import co.tpcreative.domain.repository.NewsRepository
import co.tpcreative.data.repository.FavoriteRepositoryImpl
import co.tpcreative.data.repository.NewsRepositoryImpl
import co.tpcreative.data.repository.SourceRepositoryImpl
import co.tpcreative.domain.repository.SourceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    @Named("ApiKey")
    fun provideApiKey(): String = BuildConfig.NEWS_API_KEY

    @Provides
    @Singleton
    @Named("PageSize")
    fun providePageSize(): Int = PagingConstants.DEFAULT_PAGE_SIZE

    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewsApi,
        db: NewsDatabase,
        dao: NewsDao,
        @Named("ApiKey") apiKey: String,
        @Named("PageSize") pageSize: Int
    ): NewsRepository = NewsRepositoryImpl(api, db, dao, apiKey, pageSize)

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        newsDao: NewsDao
    ): FavoriteRepository = FavoriteRepositoryImpl(newsDao)

    @Provides
    @Singleton
    fun provideSourceRepository(
        api: NewsApi,
        sourceDao: SourceDao
    ): SourceRepository = SourceRepositoryImpl(api, sourceDao)
}