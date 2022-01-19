package com.example.appstudy.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.data.model.ToDoDto
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

    private var _toDoList = MutableLiveData<List<ToDoDto>>()
    val toDoList: LiveData<List<ToDoDto>> = _toDoList

    fun fetchData(): Job = viewModelScope.launch {
        _toDoList.postValue(getToDoListUseCase())
    }
}