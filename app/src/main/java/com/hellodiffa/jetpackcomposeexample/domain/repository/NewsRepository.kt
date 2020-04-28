package com.hellodiffa.jetpackcomposeexample.domain.repository

import com.hellodiffa.jetpackcomposeexample.data.model.Article

interface NewsRepository {
    suspend fun getAllArticle(): List<Article>?
}