package co.tpcreative.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import co.tpcreative.data.database.NewsDao
import co.tpcreative.data.database.NewsDatabase
import co.tpcreative.data.mapper.toDomain
import co.tpcreative.data.network.NewsApi
import co.tpcreative.data.repository.paging.FeedPagingSource
import co.tpcreative.data.repository.paging.SearchPagingSource
import co.tpcreative.data.repository.paging.SourceNewsPagingSource
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val db: NewsDatabase,
    private val dao: NewsDao,
    private val apiKey: String,
    private val pageSize: Int
) : NewsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTopHeadlines(countryCode: String): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { FeedPagingSource(api, dao, countryCode, apiKey, pageSize) }
        ).flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override fun searchNews(query: String, sources: String?): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { SearchPagingSource(api, query, sources, apiKey, pageSize) }
        ).flow
    }

    override fun getNewsBySource(source: String): Flow<PagingData<NewsArticle>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { SourceNewsPagingSource(api, source, apiKey) }
        ).flow
    }
}