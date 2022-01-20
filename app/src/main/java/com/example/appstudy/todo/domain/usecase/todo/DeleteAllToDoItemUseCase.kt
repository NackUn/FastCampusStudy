package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.repository.ToDoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class DeleteAllToDoItemUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {
    suspend operator fun invoke() =
        toDoRepository.deleteAllToDoItem()
}
