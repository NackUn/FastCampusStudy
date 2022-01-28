package com.example.appstudy.todo.presentation.di

import com.example.appstudy.todo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get(), get(), get()) }
}