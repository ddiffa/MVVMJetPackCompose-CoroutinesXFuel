package com.hellodiffa.jetpackcomposeexample.di

import com.hellodiffa.jetpackcomposeexample.data.repository.NewsRepositoryImpl
import com.hellodiffa.jetpackcomposeexample.presentation.MainViewModel
import com.hellodiffa.jetpackcomposeexample.remote.NetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val provideNetworkModules = module {
    single { NetworkClient.provideOkHttpClient() }
    single { NetworkClient.provideRetrofit(get()) }
    single { NetworkClient.createService(get()) }
}

val provideRepository = module {
    single { NewsRepositoryImpl(get()) }
}

val provideViewModel = module {
    viewModel { MainViewModel(get(), get()) }
}