package com.hellodiffa.jetpackcomposeexample.data.repository

import com.hellodiffa.jetpackcomposeexample.data.model.Article
import com.hellodiffa.jetpackcomposeexample.domain.repository.NewsRepository
import com.hellodiffa.jetpackcomposeexample.remote.ApiService

class NewsRepositoryImpl(private val service: ApiService) :
    NewsRepository {

    override suspend fun getAllArticle(): List<Article>? =
        service.getArticle()
            .articles
            ?.map {
                Article(
                    name = it?.title,
                    description = it?.description,
                    image = it?.urlToImage
                )
            }
}