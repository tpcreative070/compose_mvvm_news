package co.tpcreative.domain.usecase

import co.tpcreative.domain.model.NewsSource
import co.tpcreative.domain.repository.SourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val sourceRepository: SourceRepository
) {
    operator fun invoke(): Flow<List<NewsSource>> = flow {
        emit(sourceRepository.getSources())
    }
}
