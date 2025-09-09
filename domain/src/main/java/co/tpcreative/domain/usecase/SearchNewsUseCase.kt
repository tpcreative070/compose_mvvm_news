package co.tpcreative.domain.usecase

import androidx.paging.PagingData
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(private val repository: NewsRepository) {
    operator fun invoke(query: String, sourceId: String?): Flow<PagingData<NewsArticle>> =
        repository.searchNews(query, sourceId)
}