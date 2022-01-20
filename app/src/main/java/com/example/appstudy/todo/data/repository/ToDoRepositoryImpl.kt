package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.repository.ToDoRepository

class ToDoRepositoryImpl : ToDoRepository {
    override suspend fun insertToDoList(todoList: List<ToDoEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getToDoList(): List<ToDoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun updateToDoItem(todoItem: ToDoEntity): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getToDoItem(id: Long): ToDoEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllToDoItem() {
        TODO("Not yet implemented")
    }
}
