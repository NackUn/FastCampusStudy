package com.nackun.domain.todo.usecase.todo

import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class DeleteTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke(id: Long): Boolean =
        todoRepository.deleteTodoItem(id)
}
