package com.example.appstudy.todo.domain.di

import com.example.appstudy.todo.domain.usecase.todo.DeleteAllToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.DeleteToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateToDoItemUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { InsertToDoListUseCase(get()) }
    factory { GetToDoListUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { InsertToDoItemUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
}
