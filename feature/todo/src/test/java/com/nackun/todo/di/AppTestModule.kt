package com.nackun.todo.di

import com.example.appstudy.todo.domain.repository.TodoRepository
import com.nackun.domain.todo.usecase.todo.DeleteAllTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.DeleteTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoListUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoListUseCase
import com.nackun.domain.todo.usecase.todo.UpdateTodoItemUseCase
import com.nackun.todo.data.repository.TestTodoRepositoryImpl
import com.nackun.todo.presentation.detail.DetailMode
import com.nackun.todo.presentation.detail.DetailViewModel
import com.nackun.todo.presentation.list.ListViewModel
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
