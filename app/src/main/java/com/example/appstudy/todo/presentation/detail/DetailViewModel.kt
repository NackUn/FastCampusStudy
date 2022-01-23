package com.example.appstudy.todo.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appstudy.todo.domain.usecase.todo.DeleteToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.GetToDoItemUseCase
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
                // TODO 나중에 작성모드로 상세화면 진입 로직 처리
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
                // TODO 나중에 작성모드로 상세화면 진입 로직 처리
            }
        }
    }
}
