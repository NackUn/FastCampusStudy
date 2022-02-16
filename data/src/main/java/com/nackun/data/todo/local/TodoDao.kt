package com.nackun.data.todo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nackun.data.todo.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    suspend fun getAll(): List<Todo>

    @Query("SELECT * FROM Todo WHERE id = :id")
    suspend fun getById(id: Long): Todo?

    @Insert
    suspend fun insert(todo: Todo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<Todo>)

    @Query("DELETE FROM Todo WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM Todo")
    suspend fun deleteAll()

    @Update
    suspend fun update(todo: Todo)
}
