package com.example.appstudy.todo.presentation.list

import com.example.appstudy.todo.domain.model.ToDoEntity

sealed class ToDoListState {
    object UnInitialized : ToDoListState()

    object Loading : ToDoListState()

    data class Success(
        val toDoList: List<ToDoEntity>
    ) : ToDoListState()

    object Error : ToDoListState()
}
