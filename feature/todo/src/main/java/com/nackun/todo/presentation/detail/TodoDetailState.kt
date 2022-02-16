package com.nackun.todo.presentation.detail

import com.example.appstudy.todo.domain.model.TodoEntity

sealed class TodoDetailState {
    object UnInitialized : TodoDetailState()

    object Loading : TodoDetailState()

    data class Success(
        val todoItem: TodoEntity
    ) : TodoDetailState()

    object Error : TodoDetailState()

    object Delete : TodoDetailState()

    object Modify : TodoDetailState()

    object Write : TodoDetailState()
}
