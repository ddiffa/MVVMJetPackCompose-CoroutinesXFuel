package com.hellodiffa.jetpackcomposeexample

import android.app.Application
import com.hellodiffa.jetpackcomposeexample.di.moduleDispatcher
import com.hellodiffa.jetpackcomposeexample.di.provideRepository
import com.hellodiffa.jetpackcomposeexample.di.provideViewModel
import org.koin.core.context.startKoin

class JetPackComposeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(provideRepository, provideViewModel, moduleDispatcher)
        }
    }
}