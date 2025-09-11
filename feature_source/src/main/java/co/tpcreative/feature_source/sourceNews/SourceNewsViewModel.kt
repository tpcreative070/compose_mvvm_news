

package co.tpcreative.feature_source.sourceNews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.usecase.GetNewsBySourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class SourceNewsViewModel @Inject constructor(
    private val getNewsBySourceUseCase: GetNewsBySourceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _newsState: MutableStateFlow<PagingData<NewsArticle>> =
        MutableStateFlow(PagingData.empty())
    val newsState: StateFlow<PagingData<NewsArticle>> = _newsState.asStateFlow()

    val sourceId: String = savedStateHandle.get<String>("sourceId") ?: ""
    val sourceName: String = URLDecoder.decode(savedStateHandle.get<String>("sourceName"), "UTF-8") ?: "Source News"

    init {
        if (sourceId.isNotEmpty()) {
            getNews(sourceId)
        }
    }

    private fun getNews(sourceId: String) {
        viewModelScope.launch {
            getNewsBySourceUseCase(sourceId)
                .cachedIn(viewModelScope)
                .collect {
                    _newsState.value = it
                }
        }
    }
}
