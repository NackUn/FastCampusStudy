package com.example.appstudy.todo.data.model

import com.example.appstudy.todo.domain.model.ToDoEntity

fun ToDoEntity.toModel(): Todo =
    Todo(id, title, description, hasCompleted)

fun Todo.toEntity(): ToDoEntity =
    ToDoEntity(id, title, description, hasCompleted)
