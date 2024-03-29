package com.nackun.data.todo.di

import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.data.todo.local.TodoDatabase
import com.nackun.data.todo.repository.TodoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TodoRepository> { TodoRepositoryImpl(get<TodoDatabase>().toDoDao()) }
}
