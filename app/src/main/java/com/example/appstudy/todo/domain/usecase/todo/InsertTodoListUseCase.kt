package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.domain.repository.TodoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class InsertTodoListUseCase(
    private val todoRepository: TodoRepository
) : UseCase {

    suspend operator fun invoke(todoList: List<TodoEntity>) {
        return todoRepository.insertTodoList(todoList)
    }
}
