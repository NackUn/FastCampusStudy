package com.nackun.domain.todo.usecase.todo

import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.domain.repository.TodoRepository
import com.nackun.domain.todo.usecase.UseCase

class InsertTodoListUseCase(
    private val todoRepository: TodoRepository
) : UseCase {

    suspend operator fun invoke(todoList: List<TodoEntity>) {
        return todoRepository.insertTodoList(todoList)
    }
}
