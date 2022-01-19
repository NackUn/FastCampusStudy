package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.repository.ToDoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class UpdateToDoItemUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {
    suspend operator fun invoke(toDoEntity: ToDoEntity): Boolean =
        toDoRepository.updateToDoItem(toDoEntity)
}
