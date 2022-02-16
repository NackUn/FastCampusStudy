package com.example.appstudy

import android.app.Application
import com.nackun.data.todo.di.databaseModule
import com.nackun.data.todo.di.repositoryModule
import com.nackun.domain.todo.di.useCaseModule
import com.nackun.todo.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
