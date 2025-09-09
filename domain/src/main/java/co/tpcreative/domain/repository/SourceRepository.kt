package co.tpcreative.domain.repository

import co.tpcreative.domain.model.NewsSource

interface SourceRepository {
    suspend fun getSources(): List<NewsSource>
}
