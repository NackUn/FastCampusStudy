package com.example.appstudy.todo.data.di

import com.example.appstudy.todo.data.repository.TodoRepositoryImpl
import com.example.appstudy.todo.data.todo.TodoDatabase
import com.example.appstudy.todo.domain.repository.ToDoRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<ToDoRepository> { TodoRepositoryImpl(get<TodoDatabase>().toDoDao()) }
}
