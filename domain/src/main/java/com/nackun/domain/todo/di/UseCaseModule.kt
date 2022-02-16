package com.nackun.domain.todo.di

import com.nackun.domain.todo.usecase.todo.DeleteAllTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.DeleteTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoListUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoListUseCase
import com.nackun.domain.todo.usecase.todo.UpdateTodoItemUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { InsertTodoListUseCase(get()) }
    factory { GetTodoListUseCase(get()) }
    factory { UpdateTodoItemUseCase(get()) }
    factory { GetTodoItemUseCase(get()) }
    factory { DeleteAllTodoItemUseCase(get()) }
    factory { InsertTodoItemUseCase(get()) }
    factory { DeleteTodoItemUseCase(get()) }
}
