package com.example.appstudy.todo.di

import com.example.appstudy.todo.data.repository.TestTodoRepositoryImpl
import com.example.appstudy.todo.domain.repository.TodoRepository
import com.example.appstudy.todo.domain.usecase.todo.DeleteAllTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.DeleteTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetTodoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateTodoItemUseCase
import com.example.appstudy.todo.presentation.detail.DetailMode
import com.example.appstudy.todo.presentation.detail.DetailViewModel
import com.example.appstudy.todo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    // ViewModel
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) ->
        DetailViewModel(
            detailMode = detailMode,
            id = id,
            get(),
            get(),
            get(),
            get()
        )
    }

    // UseCase
    factory { InsertTodoListUseCase(get()) }
    factory { GetTodoListUseCase(get()) }
    factory { UpdateTodoItemUseCase(get()) }
    factory { GetTodoItemUseCase(get()) }
    factory { DeleteAllTodoItemUseCase(get()) }
    factory { InsertTodoItemUseCase(get()) }
    factory { DeleteTodoItemUseCase(get()) }

    // Repository
    single<TodoRepository> { TestTodoRepositoryImpl() }
}
