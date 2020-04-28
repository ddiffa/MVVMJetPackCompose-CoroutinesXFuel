package com.hellodiffa.jetpackcomposeexample.data.response

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.ResponseDeserializable

data class NewsResponse(
    @Json(name = "status") val status: String,
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "articles") val listOfArticle: List<ArticlesItem> = listOf()
) {
    class Deserializer : ResponseDeserializable<NewsResponse> {
        override fun deserialize(content: String) = Klaxon().parse<NewsResponse>(content)
    }
}