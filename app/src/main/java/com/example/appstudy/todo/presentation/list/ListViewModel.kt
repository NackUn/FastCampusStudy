package com.example.appstudy.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. GetToDoUseCase
 * 2. UpdateToDoUseCase
 * 3. DeleteToDoItemUseCase
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
) : ViewModel() {

    private var _toDoList = MutableLiveData<List<ToDoEntity>>()
    val toDoList: LiveData<List<ToDoEntity>> = _toDoList

    fun fetchData(): Job = viewModelScope.launch {
        _toDoList.postValue(getToDoListUseCase())
    }
}
