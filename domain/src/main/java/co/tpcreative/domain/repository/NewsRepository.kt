package co.tpcreative.domain.repository

import androidx.paging.PagingData
import co.tpcreative.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(countryCode: String): Flow<PagingData<NewsArticle>>
    fun searchNews(query: String, sources: String?): Flow<PagingData<NewsArticle>>
    fun getNewsBySource(source: String): Flow<PagingData<NewsArticle>>
}