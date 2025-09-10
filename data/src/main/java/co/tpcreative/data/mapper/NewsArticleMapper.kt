package co.tpcreative.data.mapper

import co.tpcreative.data.database.entity.NewsArticleEntity
import co.tpcreative.domain.model.NewsArticle
import co.tpcreative.domain.model.Source

fun NewsArticleEntity.toDomain(): NewsArticle = NewsArticle(
    id = id,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(sourceId, sourceName ?: ""),
    title = title,
    url = url,
    urlToImage = urlToImage,
    isFavorite = isFavorite,
    favoritedAt = favoritedAt
)

fun NewsArticle.toEntity(): NewsArticleEntity = NewsArticleEntity(
    id = id,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = source?.id,
    sourceName = source?.name,
    title = title,
    url = url,
    urlToImage = urlToImage,
    isFavorite = isFavorite,
    favoritedAt = favoritedAt
)


