package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.repository.TodoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class DeleteAllTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke() =
        todoRepository.deleteAllTodoItem()
}
