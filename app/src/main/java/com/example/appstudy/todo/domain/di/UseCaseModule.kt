package com.example.appstudy.todo.domain.di

import com.example.appstudy.todo.domain.usecase.todo.DeleteAllTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.DeleteTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetTodoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateTodoItemUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { InsertTodoListUseCase(get()) }
    factory { GetTodoListUseCase(get()) }
    factory { UpdateTodoItemUseCase(get()) }
    factory { GetTodoItemUseCase(get()) }
    factory { DeleteAllTodoItemUseCase(get()) }
    factory { InsertTodoItemUseCase(get()) }
    factory { DeleteTodoItemUseCase(get()) }
}
