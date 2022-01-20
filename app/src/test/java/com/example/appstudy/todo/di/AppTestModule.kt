package com.example.appstudy.todo.di

import com.example.appstudy.todo.data.repository.TestToDoRepositoryImpl
import com.example.appstudy.todo.domain.repository.ToDoRepository
import com.example.appstudy.todo.domain.usecase.todo.DeleteAllToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateToDoItemUseCase
import com.example.appstudy.todo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }

    // UseCase
    factory { InsertToDoListUseCase(get()) }
    factory { GetToDoListUseCase(get()) }
    factory { UpdateToDoItemUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }

    // Repository
    single<ToDoRepository> { TestToDoRepositoryImpl() }
}
