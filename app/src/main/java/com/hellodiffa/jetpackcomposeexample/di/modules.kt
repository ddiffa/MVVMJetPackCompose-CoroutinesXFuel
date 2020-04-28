package com.hellodiffa.jetpackcomposeexample.di

import com.hellodiffa.jetpackcomposeexample.remote.NetworkClient
import org.koin.dsl.module

val provideNetworkModules = module {
    single { NetworkClient.provideOkHttpClient() }
    single { NetworkClient.provideRetrofit(get()) }
    single { NetworkClient.createService(get()) }
}