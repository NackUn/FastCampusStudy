package com.example.appstudy.todo.data.todo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appstudy.todo.domain.model.TodoEntity

@Database(
    entities = [TodoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun toDoDao(): TodoDao

    companion object {
        const val DB_NAME = "TodoDatabase.db"
    }
}
