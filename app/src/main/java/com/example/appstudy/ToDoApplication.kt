package com.example.appstudy

import android.app.Application
import com.example.appstudy.todo.data.di.databaseModule
import com.example.appstudy.todo.di.appModule
import com.example.appstudy.todo.data.di.repositoryModule
import com.example.appstudy.todo.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ToDoApplication)
            modules(
                appModule,
                databaseModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}
