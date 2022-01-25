package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.repository.TodoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class DeleteTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke(id: Long): Boolean =
        todoRepository.deleteTodoItem(id)
}
