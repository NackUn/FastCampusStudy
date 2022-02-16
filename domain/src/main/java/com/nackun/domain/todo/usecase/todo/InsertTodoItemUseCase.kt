package com.nackun.domain.todo.usecase.todo

import com.nackun.domain.todo.model.TodoEntity
import com.nackun.domain.todo.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class InsertTodoItemUseCase(
    private val todoRepository: TodoRepository
) : UseCase {

    suspend operator fun invoke(todoItem: TodoEntity): Long {
        return todoRepository.insertTodoItem(todoItem)
    }
}
