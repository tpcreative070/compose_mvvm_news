
package co.tpcreative.feature_source.sources

import co.tpcreative.domain.model.NewsSource


sealed class SourceState {
    data object Loading : SourceState()
    data class Success(val sources: List<NewsSource>) : SourceState()
    data class Error(val message: String) : SourceState()
}