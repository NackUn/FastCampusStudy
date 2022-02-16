package com.nackun.domain.todo.usecase.todo

import com.nackun.domain.todo.model.TodoEntity
import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class GetTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke(id: Long): TodoEntity? =
        todoRepository.getTodoItem(id)
}
