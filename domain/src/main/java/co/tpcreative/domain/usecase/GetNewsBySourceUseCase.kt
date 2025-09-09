package co.tpcreative.domain.usecase

import androidx.paging.PagingData
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsBySourceUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(source: String): Flow<PagingData<NewsArticle>> {
        return newsRepository.getNewsBySource(source)
    }
}
