package com.example.appstudy.todo.di

import com.example.appstudy.todo.data.repository.TestToDoRepositoryImpl
import com.example.appstudy.todo.domain.repository.ToDoRepository
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get()) }

    // UseCase
    factory { GetToDoListUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepositoryImpl() }
}
