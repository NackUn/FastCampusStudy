package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.repository.ToDoRepository

class TestToDoRepositoryImpl : ToDoRepository {

    private val toDoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun insertToDoList(todoList: List<ToDoEntity>) {
        this.toDoList.addAll(todoList)
    }

    override suspend fun getToDoList(): List<ToDoEntity> {
        return toDoList
    }

    override suspend fun updateToDoItem(todoItem: ToDoEntity): Boolean {
        return toDoList.find { it.id == todoItem.id }?.let {
            toDoList[toDoList.indexOf(it)] = todoItem
            true
        } ?: false
    }

    override suspend fun getToDoItem(id: Long): ToDoEntity? {
        return toDoList.find { it.id == id }
    }
}
