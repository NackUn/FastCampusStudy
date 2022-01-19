package com.example.appstudy.todo.data.model

data class ToDoDto(
    val id: Long = 0,
    val title: String,
    val description: String,
    val hasCompleted: Boolean = false,
)
