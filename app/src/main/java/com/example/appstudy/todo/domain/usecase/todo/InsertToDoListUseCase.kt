package com.example.appstudy.todo.domain.usecase.todo

import com.example.appstudy.todo.data.model.ToDoDto
import com.example.appstudy.todo.data.repository.ToDoRepository
import com.example.appstudy.todo.domain.usecase.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository
) : UseCase {

    suspend operator fun invoke(toDoList: List<ToDoDto>) {
        return toDoRepository.insertToDoList(toDoList)
    }
}