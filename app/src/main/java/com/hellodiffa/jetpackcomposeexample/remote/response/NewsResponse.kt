package com.hellodiffa.jetpackcomposeexample.remote.response

data class NewsResponse(
    val totalResults: Int? = null,
    val articles: List<ArticlesItem?>? = null,
    val status: String? = null
)