package com.example.appstudy.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateToDoItemUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateToDoItemUseCase]
 * 3. DeleteToDoItemUseCase
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoItemUseCase: UpdateToDoItemUseCase,
) : ViewModel() {

    private var _toDoList = MutableLiveData<List<ToDoEntity>>()
    val toDoList: LiveData<List<ToDoEntity>> = _toDoList

    fun fetchData(): Job = viewModelScope.launch {
        _toDoList.postValue(getToDoListUseCase())
    }

    fun updateItem(toDoEntity: ToDoEntity) = viewModelScope.launch {
        val isUpdate = updateToDoItemUseCase(toDoEntity)
    }
}
