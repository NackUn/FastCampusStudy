package com.example.appstudy.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.DeleteAllToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateToDoItemUseCase
import com.example.appstudy.todo.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateToDoItemUseCase]
 * 3. [deleteAllToDoItemUseCase]
 */
internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoItemUseCase: UpdateToDoItemUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase,
) : BaseViewModel() {

    private var _toDoListState = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val toDoListState: LiveData<ToDoListState> = _toDoListState

    private fun setState(toDoListState: ToDoListState) {
        _toDoListState.postValue(toDoListState)
    }

    private fun loadingState() {
        setState(ToDoListState.Loading)
    }

    override fun fetchData(): Job = viewModelScope.launch {
        loadingState()
        setState(ToDoListState.Success(getToDoListUseCase()))
    }

    fun updateItem(toDoEntity: ToDoEntity) = viewModelScope.launch {
        updateToDoItemUseCase(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        loadingState()
        deleteAllToDoItemUseCase()
        setState(ToDoListState.Success(getToDoListUseCase()))
    }
}
