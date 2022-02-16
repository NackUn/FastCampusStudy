package com.nackun.data.todo.model

import com.nackun.domain.todo.model.TodoEntity

fun TodoEntity.toModel(): Todo =
    Todo(id, title, description, hasCompleted)

fun Todo.toEntity(): TodoEntity =
    TodoEntity(id, title, description, hasCompleted)
