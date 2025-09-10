

package co.tpcreative.feature_details

import androidx.annotation.StringRes
import co.tpcreative.domain.model.NewsArticle

// Sealed class for Details Screen UI State
sealed class DetailScreenState {
    object Loading : DetailScreenState()
    data class Success(val article: NewsArticle) : DetailScreenState()
    data class Error(val message: String) : DetailScreenState()
}

sealed class DetailsViewEffect {
    data class ShowSnackbar(@StringRes val message: Int) : DetailsViewEffect()
}