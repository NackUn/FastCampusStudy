package com.nackun.todo.presentation.list

import com.nackun.domain.todo.model.TodoEntity

sealed class TodoListState {
    object UnInitialized : TodoListState()

    object Loading : TodoListState()

    data class Success(
        val todoList: List<TodoEntity>
    ) : TodoListState()

    object Error : TodoListState()
}
