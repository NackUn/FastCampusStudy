package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.data.model.ToDoDto

/**
 * 1. insertToDoList
 * 2. getToDoList
 */
interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoDto>

    suspend fun insertToDoList(todoList: List<ToDoDto>)
}