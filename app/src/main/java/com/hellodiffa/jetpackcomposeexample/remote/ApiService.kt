package com.hellodiffa.jetpackcomposeexample.remote

import com.hellodiffa.jetpackcomposeexample.remote.response.NewsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=id&category=technology&apiKey=apiKey")
    suspend fun getArticle(): NewsResponse
}