package com.nackun.domain.todo.usecase.todo

import com.example.appstudy.todo.domain.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class DeleteAllTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke() =
        todoRepository.deleteAllTodoItem()
}
