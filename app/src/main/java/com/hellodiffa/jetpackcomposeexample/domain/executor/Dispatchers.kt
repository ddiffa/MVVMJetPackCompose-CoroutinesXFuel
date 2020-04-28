package com.hellodiffa.jetpackcomposeexample.domain.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
interface Dispatchers {

    companion object {
        val DEFAULT: Dispatchers =
            object :
                Dispatchers {
                override fun ioDispatchers(): CoroutineDispatcher = IO
            }
    }


    fun ioDispatchers(): CoroutineDispatcher
}