package com.nackun.todo.presentation.list

import com.example.appstudy.todo.domain.model.TodoEntity

sealed class TodoListState {
    object UnInitialized : TodoListState()

    object Loading : TodoListState()

    data class Success(
        val todoList: List<TodoEntity>
    ) : TodoListState()

    object Error : TodoListState()
}
