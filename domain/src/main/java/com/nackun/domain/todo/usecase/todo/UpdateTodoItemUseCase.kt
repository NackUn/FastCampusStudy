package com.nackun.domain.todo.usecase.todo

import com.nackun.domain.todo.model.TodoEntity
import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class UpdateTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke(todoEntity: TodoEntity): Boolean =
        todoRepository.updateTodoItem(todoEntity)
}
