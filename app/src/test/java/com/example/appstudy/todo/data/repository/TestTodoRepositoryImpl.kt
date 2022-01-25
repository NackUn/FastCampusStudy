package com.example.appstudy.todo.data.repository

import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.domain.repository.TodoRepository

class TestTodoRepositoryImpl : TodoRepository {

    private val todoList: MutableList<TodoEntity> = mutableListOf()

    override suspend fun insertTodoList(todoList: List<TodoEntity>) {
        this.todoList.addAll(todoList)
    }

    override suspend fun getTodoList(): List<TodoEntity> {
        return mutableListOf<TodoEntity>().apply { addAll(todoList) }
    }

    override suspend fun updateTodoItem(todoItem: TodoEntity): Boolean {
        return todoList.find { it.id == todoItem.id }?.let {
            todoList[todoList.indexOf(it)] = todoItem
            true
        } ?: false
    }

    override suspend fun getTodoItem(id: Long): TodoEntity? {
        return todoList.find { it.id == id }
    }

    override suspend fun deleteAllTodoItem() {
        todoList.clear()
    }

    override suspend fun insertTodoItem(todoItem: TodoEntity): Long {
        this.todoList.add(todoItem)
        return todoItem.id
    }

    override suspend fun deleteTodoItem(id: Long): Boolean {
        return todoList.remove(todoList.find { it.id == id })
    }
}
