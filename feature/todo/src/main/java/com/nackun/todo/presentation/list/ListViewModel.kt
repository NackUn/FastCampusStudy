package com.nackun.todo.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nackun.base.BaseViewModel
import com.nackun.domain.todo.usecase.todo.DeleteAllTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoListUseCase
import com.nackun.domain.todo.usecase.todo.UpdateTodoItemUseCase
import com.nackun.todo.presentation.util.toEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 UseCase
 * 1. [GetTodoListUseCase]
 * 2. [UpdateTodoItemUseCase]
 * 3. [deleteAllTodoItemUseCase]
 */
class ListViewModel(
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
