package com.app.tracky.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val readAllData: LiveData<List<TodoElement>> = todoDao.readAllData()

    suspend fun addTodo(todo: TodoElement){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: TodoElement){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoElement){
        todoDao.deleteTodo(todo)
    }
}