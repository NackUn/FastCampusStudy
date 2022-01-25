package com.example.appstudy.todo.data.model

import com.example.appstudy.todo.domain.model.TodoEntity

fun TodoEntity.toModel(): Todo =
    Todo(id, title, description, hasCompleted)

fun Todo.toEntity(): TodoEntity =
    TodoEntity(id, title, description, hasCompleted)
