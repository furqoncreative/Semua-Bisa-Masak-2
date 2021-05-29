package com.furqoncreative.semuabisamasak

import android.app.Application
import com.furqoncreative.core.di.databaseModule
import com.furqoncreative.core.di.networkModule
import com.furqoncreative.core.di.repositoryModule
import com.furqoncreative.semuabisamasak.di.useCaseModule
import com.furqoncreative.semuabisamasak.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}