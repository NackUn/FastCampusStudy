package com.example.appstudy.todo.presentation.list

data class ListTodoModel(
    val id: Long = 0,
    val title: String,
    val description: String,
    val hasCompleted: Boolean = false,
)
