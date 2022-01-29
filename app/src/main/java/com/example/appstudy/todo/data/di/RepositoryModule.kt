package com.example.appstudy.todo.data.di

import com.example.appstudy.todo.data.repository.TodoRepositoryImpl
import com.example.appstudy.todo.data.local.TodoDatabase
import com.example.appstudy.todo.domain.repository.TodoRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<TodoRepository> { TodoRepositoryImpl(get<TodoDatabase>().toDoDao()) }
}
