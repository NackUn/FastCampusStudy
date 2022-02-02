package com.example.appstudy.todo

import android.app.Application
import com.example.appstudy.todo.data.di.databaseModule
import com.example.appstudy.todo.data.di.repositoryModule
import com.example.appstudy.todo.domain.di.useCaseModule
import com.example.appstudy.todo.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@TodoApplication)
            modules(
                databaseModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}
