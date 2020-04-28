package com.hellodiffa.jetpackcomposeexample.domain.repository

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.hellodiffa.jetpackcomposeexample.data.response.NewsResponse

interface NewsRepository {
    suspend fun getAllArticle(): Result<NewsResponse, FuelError>
}