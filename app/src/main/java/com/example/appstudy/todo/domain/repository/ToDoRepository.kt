package com.example.appstudy.todo.domain.repository

import com.example.appstudy.todo.domain.model.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 */
interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(todoList: List<ToDoEntity>)
}
