package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.data.model.ToDoDto

class TestToDoRepositoryImpl : ToDoRepository {

    private val toDoList: MutableList<ToDoDto> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoDto> {
        return toDoList
    }

    override suspend fun insertToDoList(todoList: List<ToDoDto>) {
        this.toDoList.addAll(todoList)
    }
}