package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.data.model.ToDoDto

class ToDoRepositoryImpl : ToDoRepository {
    override suspend fun getToDoList(): List<ToDoDto> {
        TODO("Not yet implemented")
    }

    override suspend fun insertToDoList(todoList: List<ToDoDto>) {
        TODO("Not yet implemented")
    }
}