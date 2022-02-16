package com.nackun.domain.todo.repository

import com.nackun.domain.todo.model.TodoEntity

/**
 * 1. [insertTodoList]
 * 2. [getTodoList]
 * 3. [updateTodoItem]
 * 4. [getTodoItem]
 * 5. [deleteAllTodoItem]
 * 6. [insertTodoItem]
 * 7. [deleteTodoItem]
 */
interface TodoRepository {
    suspend fun insertTodoList(todoList: List<TodoEntity>)
    suspend fun getTodoList(): List<TodoEntity>
    suspend fun updateTodoItem(todoItem: TodoEntity): Boolean
    suspend fun getTodoItem(id: Long): TodoEntity?
    suspend fun deleteAllTodoItem()
    suspend fun insertTodoItem(todoItem: TodoEntity): Long
    suspend fun deleteTodoItem(id: Long): Boolean
}
