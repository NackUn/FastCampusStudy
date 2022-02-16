package com.nackun.todo.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nackun.domain.todo.model.TodoEntity
import com.nackun.domain.todo.usecase.todo.DeleteTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.GetTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.UpdateTodoItemUseCase
import com.nackun.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(
    var detailMode: DetailMode,
    var id: Long = -1,
    private val getTodoItemUseCase: GetTodoItemUseCase,
    private val deleteTodoItemUseCase: DeleteTodoItemUseCase,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase,
    private val insertTodoItemUseCase: InsertTodoItemUseCase,
) : BaseViewModel() {

    private var _toDoItemState = MutableLiveData<TodoDetailState>(TodoDetailState.UnInitialized)
    val todoItemState: LiveData<TodoDetailState> = _toDoItemState

    override fun fetchData(): Job = viewModelScope.launch {
        when (detailMode) {
            DetailMode.DETAIL -> {
                loadingState()
                try {
                    getTodoItemUseCase(id)?.let {
                        _toDoItemState.postValue(TodoDetailState.Success(it))
                    } ?: kotlin.run {
                        _toDoItemState.postValue(TodoDetailState.Error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(TodoDetailState.Error)
                }
            }
            DetailMode.WRITE -> {
                _toDoItemState.postValue(TodoDetailState.Write)
            }
        }
    }

    private fun loadingState() {
        _toDoItemState.postValue(TodoDetailState.Loading)
    }

    fun deleteTodoItem() = viewModelScope.launch {
        try {
            if (deleteTodoItemUseCase(id)) {
                _toDoItemState.postValue(TodoDetailState.Delete)
            } else {
                _toDoItemState.postValue(TodoDetailState.Error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _toDoItemState.postValue(TodoDetailState.Error)
        }
    }

    fun updateTodoItem(
        title: String? = null,
        description: String? = null,
        hasComplete: Boolean? = null
    ) = viewModelScope.launch {
        loadingState()
        when (detailMode) {
            DetailMode.DETAIL -> {
                try {
                    getTodoItemUseCase(id)?.let {
                        val currentTitle = title ?: it.title
                        val currentDescription = description ?: it.description
                        val currentHasComplete = hasComplete ?: it.hasCompleted

                        val currentTodoItem = it.copy(
                            title = currentTitle,
                            description = currentDescription,
                            hasCompleted = currentHasComplete
                        )
                        if (updateTodoItemUseCase(currentTodoItem)) {
                            _toDoItemState.postValue(TodoDetailState.Success(currentTodoItem))
                        } else {
                            _toDoItemState.postValue(TodoDetailState.Error)
                        }
                    } ?: kotlin.run {
                        _toDoItemState.postValue(TodoDetailState.Error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(TodoDetailState.Error)
                }
            }
            DetailMode.WRITE -> {
                try {
                    val toDoEntity = TodoEntity(
                        title = title ?: "",
                        description = description ?: "",
                        hasCompleted = hasComplete ?: false
                    )
                    val insertId = insertTodoItemUseCase(toDoEntity)
                    _toDoItemState.postValue(TodoDetailState.Success(toDoEntity))
                    detailMode = DetailMode.DETAIL
                    id = insertId
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(TodoDetailState.Error)
                }
            }
        }
    }

    fun setModifyMode() = viewModelScope.launch {
        _toDoItemState.postValue(TodoDetailState.Modify)
    }
}
