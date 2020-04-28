package com.hellodiffa.jetpackcomposeexample.data.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.result.Result
import com.hellodiffa.jetpackcomposeexample.domain.executor.Dispatchers
import com.hellodiffa.jetpackcomposeexample.domain.repository.NewsRepository
import com.hellodiffa.jetpackcomposeexample.data.response.NewsResponse

class NewsRepositoryImpl(private val dispatchers: Dispatchers) :
    NewsRepository {

    override suspend fun getAllArticle(): Result<NewsResponse, FuelError> {
        return Fuel.get("https://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=efd67bbc1d024b32a17469d00124ec15")
            .awaitObjectResult(NewsResponse.Deserializer(), dispatchers.ioDispatchers())
    }

}