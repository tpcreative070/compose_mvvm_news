package com.rafsan.newsapp.data.repository

import co.tpcreative.core.util.Constants
import co.tpcreative.data.database.SourceDao
import co.tpcreative.data.database.entity.SourceEntity
import co.tpcreative.data.network.NewsApi
import co.tpcreative.domain.model.NewsSource
import co.tpcreative.domain.repository.SourceRepository
import com.rafsan.newsapp.domain.model.NewsSource
import com.rafsan.newsapp.domain.repository.SourceRepository
import com.rafsan.newsapp.core.util.Constants
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SourceRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val sourceDao: SourceDao
) : SourceRepository {

    override suspend fun getSources(): List<NewsSource> {
        val cachedSources = sourceDao.getSources()
        val firstSource = cachedSources.firstOrNull()

        if (firstSource != null && !isCacheExpired(firstSource.createdAt)) {
            return cachedSources.map { it.toNewsSource() }
        }

        val response = newsApi.getSources(BuildConfig.NEWS_API_KEY)
        if (response.isSuccessful) {
            val sources = response.body()?.sources ?: emptyList()
            sourceDao.deleteAllSources()
            sourceDao.insertSources(sources.map { it.toSourceEntity() })
            return sources
        }

        return emptyList()
    }

    private fun isCacheExpired(createdAt: Long): Boolean {
        val cacheLifetimeInMillis = TimeUnit.DAYS.toMillis(Constants.CACHE_LIFETIME_IN_DAYS.toLong())
        return System.currentTimeMillis() - createdAt > cacheLifetimeInMillis
    }
}

fun SourceEntity.toNewsSource(): NewsSource {
    return NewsSource(
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country
    )
}

fun NewsSource.toSourceEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name,
        description = description,
        url = url,
        category = category,
        language = language,
        country = country,
        createdAt = System.currentTimeMillis()
    )
}
