package com.hellodiffa.jetpackcomposeexample.di

import com.hellodiffa.jetpackcomposeexample.data.repository.NewsRepositoryImpl
import com.hellodiffa.jetpackcomposeexample.domain.executor.Dispatchers
import com.hellodiffa.jetpackcomposeexample.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val provideRepository = module {
    single { NewsRepositoryImpl(get()) }
}

val provideViewModel = module {
    viewModel { MainViewModel(get()) }
}

val moduleDispatcher = module {
    single { Dispatchers.DEFAULT }
}