package com.app.tracky.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoRepository

    init{
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
    }

    val todos = repository.readAllData

    fun addTodo(todo : TodoElement) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo : TodoElement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo : TodoElement) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }
}