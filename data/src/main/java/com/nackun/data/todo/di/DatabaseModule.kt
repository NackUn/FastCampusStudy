package com.nackun.data.todo.di

import androidx.room.Room
import com.nackun.data.todo.local.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            TodoDatabase.DB_NAME
        )
            .build()
    }
}
