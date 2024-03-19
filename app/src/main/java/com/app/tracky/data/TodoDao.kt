package com.app.tracky.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: TodoElement)

    @Update
    suspend fun updateTodo(todo: TodoElement)

    @Delete
    suspend fun deleteTodo(todo: TodoElement)

    @Query("SELECT * FROM todoElement")
    fun readAllData(): LiveData<List<TodoElement>>
}