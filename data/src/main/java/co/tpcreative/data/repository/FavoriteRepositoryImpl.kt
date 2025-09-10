package co.tpcreative.data.repository

import co.tpcreative.data.database.NewsDao
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.repository.FavoriteRepository
import co.tpcreative.data.mapper.toDomain
import co.tpcreative.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao
) : FavoriteRepository {

    override fun getFavorites(): Flow<List<NewsArticle>> {
        return newsDao.getFavoriteNews().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addFavorite(article: NewsArticle): Long {
        // When adding a favorite, we ensure the flag is set to true and record the time.
        return newsDao.upsert(
            article.copy(
                isFavorite = true,
                favoritedAt = System.currentTimeMillis()
            ).toEntity()
        )
    }

    override suspend fun removeFavorite(article: NewsArticle) {
        newsDao.deleteNews(article.toEntity())
    }

    override suspend fun isFavorite(articleUrl: String): Boolean {
        return newsDao.getFavoriteArticleByUrl(articleUrl) != null
    }

    override suspend fun removeAllFavorites() {
        newsDao.deleteAllNews()
    }
}


