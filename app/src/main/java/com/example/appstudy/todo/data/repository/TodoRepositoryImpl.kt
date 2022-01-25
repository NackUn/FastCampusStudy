package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.data.model.toEntity
import com.example.appstudy.todo.data.model.toModel
import com.example.appstudy.todo.data.todo.TodoDao
import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.repository.ToDoRepository

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : ToDoRepository {
    override suspend fun insertToDoList(todoList: List<ToDoEntity>) {
        todoDao.insertAll(todoList.map { it.toModel() })
    }

    override suspend fun getToDoList(): List<ToDoEntity> =
        todoDao.getAll().map { it.toEntity() }

    override suspend fun updateToDoItem(todoItem: ToDoEntity): Boolean =
        todoDao.update(todoItem.toModel())

    override suspend fun getToDoItem(id: Long): ToDoEntity? =
        todoDao.getById(id)?.toEntity()

    override suspend fun deleteAllToDoItem() {
        todoDao.deleteAll()
    }

    override suspend fun insertToDoItem(todoItem: ToDoEntity): Long =
        todoDao.insert(todoItem.toModel())

    override suspend fun deleteToDoItem(id: Long): Boolean =
        todoDao.deleteById(id)
}
