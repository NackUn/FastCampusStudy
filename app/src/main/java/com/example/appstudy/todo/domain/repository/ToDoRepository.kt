package com.example.appstudy.todo.domain.repository

import com.example.appstudy.todo.domain.model.ToDoEntity

/**
 * 1. [insertToDoList]
 * 2. [getToDoList]
 * 3. [updateToDoItem]
 * 4. [getToDoItem]
 * 5. [deleteAllToDoItem]
 * 6. [insertToDoItem]
 * 7. [deleteToDoItem]
 */
interface ToDoRepository {
    suspend fun insertToDoList(todoList: List<ToDoEntity>)
    suspend fun getToDoList(): List<ToDoEntity>
    suspend fun updateToDoItem(todoItem: ToDoEntity): Boolean
    suspend fun getToDoItem(id: Long): ToDoEntity?
    suspend fun deleteAllToDoItem()
    suspend fun insertToDoItem(todoItem: ToDoEntity)
    suspend fun deleteToDoItem(id: Long): Boolean
}
