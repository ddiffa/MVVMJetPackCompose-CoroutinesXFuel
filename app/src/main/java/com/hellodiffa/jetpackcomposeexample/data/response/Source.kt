package com.hellodiffa.jetpackcomposeexample.data.response

import com.beust.klaxon.Json

data class Source(
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?
)