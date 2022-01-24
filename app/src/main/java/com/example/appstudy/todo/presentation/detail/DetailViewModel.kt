package com.example.appstudy.todo.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.DeleteToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.UpdateToDoItemUseCase
import com.example.appstudy.todo.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class DetailViewModel(
    var detailMode: DetailMode,
    var id: Long = -1,
    private val getToDoItemUseCase: GetToDoItemUseCase,
    private val deleteToDoItemUseCase: DeleteToDoItemUseCase,
    private val updateToDoItemUseCase: UpdateToDoItemUseCase,
    private val insertToDoItemUseCase: InsertToDoItemUseCase,
) : BaseViewModel() {

    private var _toDoItemState = MutableLiveData<ToDoDetailState>(ToDoDetailState.UnInitialized)
    val toDoItemState: LiveData<ToDoDetailState> = _toDoItemState

    override fun fetchData(): Job = viewModelScope.launch {
        when (detailMode) {
            DetailMode.DETAIL -> {
                loadingState()
                try {
                    getToDoItemUseCase(id)?.let {
                        _toDoItemState.postValue(ToDoDetailState.Success(it))
                    } ?: kotlin.run {
                        _toDoItemState.postValue(ToDoDetailState.Error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(ToDoDetailState.Error)
                }
            }
            DetailMode.WRITE -> {
                _toDoItemState.postValue(ToDoDetailState.Write)
            }
        }
    }

    private fun loadingState() {
        _toDoItemState.postValue(ToDoDetailState.Loading)
    }

    fun deleteToDoItem() = viewModelScope.launch {
        try {
            if (deleteToDoItemUseCase(id)) {
                _toDoItemState.postValue(ToDoDetailState.Delete)
            } else {
                _toDoItemState.postValue(ToDoDetailState.Error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _toDoItemState.postValue(ToDoDetailState.Error)
        }
    }

    fun updateToDoItem(
        title: String? = null,
        description: String? = null,
        hasComplete: Boolean? = null
    ) = viewModelScope.launch {
        loadingState()
        when (detailMode) {
            DetailMode.DETAIL -> {
                try {
                    getToDoItemUseCase(id)?.let {
                        val currentTitle = title ?: it.title
                        val currentDescription = description ?: it.description
                        val currentHasComplete = hasComplete ?: it.hasCompleted

                        val currentToDoItem = it.copy(
                            title = currentTitle,
                            description = currentDescription,
                            hasCompleted = currentHasComplete
                        )
                        if (updateToDoItemUseCase(currentToDoItem)) {
                            _toDoItemState.postValue(ToDoDetailState.Success(currentToDoItem))
                        } else {
                            _toDoItemState.postValue(ToDoDetailState.Error)
                        }
                    } ?: kotlin.run {
                        _toDoItemState.postValue(ToDoDetailState.Error)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(ToDoDetailState.Error)
                }
            }
            DetailMode.WRITE -> {
                try {
                    val toDoEntity = ToDoEntity(
                        title = title ?: "",
                        description = description ?: "",
                        hasCompleted = hasComplete ?: false
                    )
                    val insertId = insertToDoItemUseCase(toDoEntity)
                    _toDoItemState.postValue(ToDoDetailState.Success(toDoEntity))
                    detailMode = DetailMode.DETAIL
                    id = insertId
                } catch (e: Exception) {
                    e.printStackTrace()
                    _toDoItemState.postValue(ToDoDetailState.Error)
                }
            }
        }
    }
}
