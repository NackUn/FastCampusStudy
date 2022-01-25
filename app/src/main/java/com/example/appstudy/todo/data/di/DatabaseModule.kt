package com.example.appstudy.todo.data.di

import androidx.room.Room
import com.example.appstudy.todo.data.todo.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            TodoDatabase.DB_NAME
        )
            .build()
    }
}
