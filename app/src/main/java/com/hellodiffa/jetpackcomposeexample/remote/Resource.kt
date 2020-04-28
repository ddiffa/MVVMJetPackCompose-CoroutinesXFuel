package com.hellodiffa.jetpackcomposeexample.remote

data class Resource<out T>(val data: T?, val errorMessage: String?, val status: Status) {

    companion object {

        fun <T> success(data: T): Resource<T> =
            Resource(data = data, errorMessage = null, status = Status.SUCCESS)

        fun <T> error(errorMessage: String?): Resource<T> =
            Resource(data = null, errorMessage = errorMessage, status = Status.ERROR)

        fun <T> loading(): Resource<T> =
            Resource(data = null, errorMessage = null, status = Status.LOADING)
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}