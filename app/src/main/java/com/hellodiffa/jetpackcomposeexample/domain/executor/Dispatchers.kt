package com.hellodiffa.jetpackcomposeexample.domain.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

interface Dispatchers {

    companion object {
        val DEFAULT: Dispatchers =
            object :
                Dispatchers {
                override fun ioDispatchers(): CoroutineDispatcher = IO
                override fun mainDispatchers(): CoroutineDispatcher = Main
            }
    }


    fun ioDispatchers(): CoroutineDispatcher
    fun mainDispatchers(): CoroutineDispatcher
}