package com.example.appstudy.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.usecase.todo.DeleteAllTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetTodoListUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateTodoItemUseCase
import com.example.appstudy.todo.presentation.base.BaseViewModel
import com.example.appstudy.todo.presentation.util.toEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetTodoListUseCase]
 * 2. [UpdateTodoItemUseCase]
 * 3. [deleteAllTodoItemUseCase]
 */
internal class ListViewModel(
    private val getTodoListUseCase: GetTodoListUseCase,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase,
    private val deleteAllTodoItemUseCase: DeleteAllTodoItemUseCase,
) : BaseViewModel() {

    private var _toDoListState = MutableLiveData<TodoListState>(TodoListState.UnInitialized)
    val todoListState: LiveData<TodoListState> = _toDoListState

    private fun setState(todoListState: TodoListState) {
        _toDoListState.postValue(todoListState)
    }

    private fun loadingState() {
        setState(TodoListState.Loading)
    }

    override fun fetchData(): Job = viewModelScope.launch {
        loadingState()
        setState(TodoListState.Success(getTodoListUseCase()))
    }

    fun updateItem(listTodoModel: ListTodoModel) = viewModelScope.launch {
        updateTodoItemUseCase(listTodoModel.toEntity())
    }

    fun deleteAll() = viewModelScope.launch {
        loadingState()
        deleteAllTodoItemUseCase()
        setState(TodoListState.Success(getTodoListUseCase()))
    }
}
