package com.nackun.todo.presentation.di

import com.nackun.todo.presentation.detail.DetailViewModel
import com.nackun.todo.presentation.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { DetailViewModel(get(), get(), get(), get(), get(), get()) }
}
