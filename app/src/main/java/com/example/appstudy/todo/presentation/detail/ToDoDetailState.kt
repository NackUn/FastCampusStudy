package com.example.appstudy.todo.presentation.detail

import com.example.appstudy.todo.domain.model.ToDoEntity

sealed class ToDoDetailState {
    object UnInitialized : ToDoDetailState()

    object Loading : ToDoDetailState()

    data class Success(
        val toDoItem: ToDoEntity
    ) : ToDoDetailState()

    object Error : ToDoDetailState()

    object Delete : ToDoDetailState()

    object Modify : ToDoDetailState()

    object Write : ToDoDetailState()
}
