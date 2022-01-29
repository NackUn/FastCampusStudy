package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.data.model.toEntity
import com.example.appstudy.todo.data.model.toModel
import com.example.appstudy.todo.data.local.TodoDao
import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val todoDao: TodoDao,
) : TodoRepository {
    override suspend fun insertTodoList(todoList: List<TodoEntity>) {
        todoDao.insertAll(todoList.map { it.toModel() })
    }

    override suspend fun getTodoList(): List<TodoEntity> =
        todoDao.getAll().map { it.toEntity() }

    override suspend fun updateTodoItem(todoItem: TodoEntity): Boolean =
        try {
            todoDao.update(todoItem.toModel())
            true
        } catch (e: Exception) {
            false
        }

    override suspend fun getTodoItem(id: Long): TodoEntity? =
        todoDao.getById(id)?.toEntity()

    override suspend fun deleteAllTodoItem() {
        todoDao.deleteAll()
    }

    override suspend fun insertTodoItem(todoItem: TodoEntity): Long =
        todoDao.insert(todoItem.toModel())

    override suspend fun deleteTodoItem(id: Long): Boolean =
        try {
            todoDao.deleteById(id)
            true
        } catch (e: Exception) {
            false
        }
}
