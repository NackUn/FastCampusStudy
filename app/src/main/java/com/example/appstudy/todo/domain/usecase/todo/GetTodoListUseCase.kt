package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.repository.TodoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class GetTodoListUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke() = todoRepository.getTodoList()
}
