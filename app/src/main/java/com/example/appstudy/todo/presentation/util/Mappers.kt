package com.example.appstudy.todo.presentation.util

import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.presentation.list.ListTodoModel

internal fun List<TodoEntity>.toModel(): List<ListTodoModel> = this.map {
    it.toModel()
}

internal fun TodoEntity.toModel(): ListTodoModel = ListTodoModel(
    id, title, description, hasCompleted
)

internal fun ListTodoModel.toEntity(): TodoEntity = TodoEntity(
    id, title, description, hasCompleted
)
