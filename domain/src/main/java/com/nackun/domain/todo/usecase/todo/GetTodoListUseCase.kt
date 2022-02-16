package com.nackun.domain.todo.usecase.todo

import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class GetTodoListUseCase(
    private val todoRepository: TodoRepository
) : UseCase {
    suspend operator fun invoke() = todoRepository.getTodoList()
}
